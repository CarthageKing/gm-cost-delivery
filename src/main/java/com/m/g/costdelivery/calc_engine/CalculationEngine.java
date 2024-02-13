package com.m.g.costdelivery.calc_engine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

import com.m.g.costdelivery.calc_engine.FhirPathReducedParser.AdditiveExpressionContext;
import com.m.g.costdelivery.calc_engine.FhirPathReducedParser.AndExpressionContext;
import com.m.g.costdelivery.calc_engine.FhirPathReducedParser.BooleanLiteralContext;
import com.m.g.costdelivery.calc_engine.FhirPathReducedParser.EqualityExpressionContext;
import com.m.g.costdelivery.calc_engine.FhirPathReducedParser.ExternalConstantTermContext;
import com.m.g.costdelivery.calc_engine.FhirPathReducedParser.FunctionInvocationContext;
import com.m.g.costdelivery.calc_engine.FhirPathReducedParser.InequalityExpressionContext;
import com.m.g.costdelivery.calc_engine.FhirPathReducedParser.MultiplicativeExpressionContext;
import com.m.g.costdelivery.calc_engine.FhirPathReducedParser.NumberLiteralContext;
import com.m.g.costdelivery.calc_engine.FhirPathReducedParser.OrExpressionContext;
import com.m.g.costdelivery.calc_engine.FhirPathReducedParser.ParenthesizedTermContext;
import com.m.g.costdelivery.exception.CalculationEngineException;
import com.m.g.costdelivery.util.Util;

public class CalculationEngine {

	private final ParseTree expressionTree;
	private final List<String> errors;

	// use banker's rounding for internal calculations
	// https://docs.oracle.com/javase/8/docs/api/java/math/RoundingMode.html#HALF_EVEN
	private final RoundingMode roundingMode = RoundingMode.HALF_EVEN;
	private final int maxScale = 4;

	public CalculationEngine(String expression) {
		CodePointCharStream charStream = CharStreams.fromString(expression);
		FhirPathReducedLexer lexer = new FhirPathReducedLexer(charStream);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		FhirPathReducedParser parser = new FhirPathReducedParser(tokenStream);
		CalculationEngineErrorListener errListener = new CalculationEngineErrorListener();
		parser.addErrorListener(errListener);
		expressionTree = parser.expression();
		errors = errListener.getErrors();
	}

	public List<String> getErrors() {
		return errors;
	}

	public Object evaluate(BigDecimal weight, BigDecimal height, BigDecimal width, BigDecimal length) {
		CalcEngine ce = new CalcEngine(weight, height, width, length);
		return ce.doEval();
	}

	private class CalcEngine {

		private final BigDecimal weight;
		private final BigDecimal height;
		private final BigDecimal width;
		private final BigDecimal length;
		private final BigDecimal volume;

		public CalcEngine(BigDecimal weight, BigDecimal height, BigDecimal width, BigDecimal length) {
			this.weight = weight;
			this.height = height;
			this.width = width;
			this.length = length;
			volume = height.multiply(width).multiply(length);
		}

		public Object doEval() {
			Object result = innerEval(expressionTree);
			if (result instanceof Boolean) {
				return result;
			} else if (result instanceof BigDecimal) {
				BigDecimal r = (BigDecimal) result;
				return Util.stripTrailingZerosAfterDecimalPoint(r);
			}
			throw new CalculationEngineException("Invalid expression");
		}

		private Object innerEval(ParseTree node) {
			if (node instanceof ExternalConstantTermContext) {
				ParseTree inner = node.getChild(0); // gets ExternalConstantContext
				inner = inner.getChild(1); // gets TerminalNodeImpl that contains the string we want
				String identifier = extractExternConstant(inner.getText());

				if ("weight".equals(identifier)) {
					return weight;
				} else if ("height".equals(identifier)) {
					return height;
				} else if ("width".equals(identifier)) {
					return width;
				} else if ("length".equals(identifier)) {
					return length;
				} else if ("volume".equals(identifier)) {
					return volume;
				} else {
					throw new CalculationEngineException("Unrecognized variable: " + identifier);
				}
			}

			if (node instanceof NumberLiteralContext) {
				return new BigDecimal(node.getText());
			}

			if (node instanceof BooleanLiteralContext) {
				return Boolean.valueOf(node.getText());
			}

			if (node instanceof AdditiveExpressionContext) {
				return doAdditiveOper(node);
			}

			if (node instanceof MultiplicativeExpressionContext) {
				return doMultiplicativeOper(node);
			}

			if (node instanceof InequalityExpressionContext) {
				return doInequalityOper(node);
			}

			if (node instanceof EqualityExpressionContext) {
				return doEqualityOper(node);
			}

			if (node instanceof AndExpressionContext) {
				return doAndOper(node);
			}

			if (node instanceof OrExpressionContext) {
				return doOrOper(node);
			}

			if (node instanceof FunctionInvocationContext) {
				throw new CalculationEngineException("Functions are not yet supported");
			}

			if (node instanceof ParenthesizedTermContext) {
				ParseTree inner = node.getChild(1); // get the actual content. index 0 and index 2 contain the terminal nodes ( and )
				return innerEval(inner);
			} else {
				ParseTree inner = node.getChild(0);
				return innerEval(inner);
			}
		}

		private Object doOrOper(ParseTree node) {
			Object lhs = innerEval(node.getChild(0));
			String operStr = node.getChild(1).getText();
			OrOper o = OrOper.parseValue(operStr);
			Object rhs = innerEval(node.getChild(2));

			if (lhs instanceof Boolean && rhs instanceof Boolean) {
				boolean left = (boolean) lhs;
				boolean right = (boolean) rhs;
				switch (o) {
				case OR:
					return left || right;

				case XOR:
					return left ^ right;

				default:
					// falls through
				}
			} else {
				throwIncompatibleTypes(lhs, operStr, rhs, Arrays.asList(Boolean.class));
				return null;
			}

			throw new CalculationEngineException("Unsupported or operation: " + operStr);
		}

		private Object doAndOper(ParseTree node) {
			Object lhs = innerEval(node.getChild(0));
			String operStr = node.getChild(1).getText();
			AndOper o = AndOper.parseValue(operStr);
			Object rhs = innerEval(node.getChild(2));

			if (lhs instanceof Boolean && rhs instanceof Boolean) {
				boolean left = (boolean) lhs;
				boolean right = (boolean) rhs;
				switch (o) {
				case AND:
					return left && right;

				default:
					// falls through
				}
			} else {
				throwIncompatibleTypes(lhs, operStr, rhs, Arrays.asList(Boolean.class));
				return null;
			}

			throw new CalculationEngineException("Unsupported and operation: " + operStr);
		}

		private Object doEqualityOper(ParseTree node) {
			Object lhs = innerEval(node.getChild(0));
			String operStr = node.getChild(1).getText();
			EqualityOper o = EqualityOper.parseValue(operStr);
			Object rhs = innerEval(node.getChild(2));

			if (lhs instanceof BigDecimal && rhs instanceof BigDecimal) {
				BigDecimal left = (BigDecimal) lhs;
				BigDecimal right = (BigDecimal) rhs;
				switch (o) {
				case EQ:
					return 0 == left.compareTo(right);

				case NEQ:
					return 0 != left.compareTo(right);

				default:
					// falls through
				}
			} else if (lhs instanceof Boolean && rhs instanceof Boolean) {
				boolean left = (boolean) lhs;
				boolean right = (boolean) rhs;
				switch (o) {
				case EQ:
					return left == right;

				case NEQ:
					return left != right;

				default:
					// falls through
				}
			} else {
				throwIncompatibleTypes(lhs, operStr, rhs, Arrays.asList(Boolean.class, BigDecimal.class));
				return null;
			}

			throw new CalculationEngineException("Unsupported equality operation: " + operStr);
		}

		private Object doInequalityOper(ParseTree node) {
			Object lhs = innerEval(node.getChild(0));
			String operStr = node.getChild(1).getText();
			InequalityOper o = InequalityOper.parseValue(operStr);
			Object rhs = innerEval(node.getChild(2));

			if (lhs instanceof BigDecimal && rhs instanceof BigDecimal) {
				BigDecimal left = (BigDecimal) lhs;
				BigDecimal right = (BigDecimal) rhs;
				switch (o) {
				case LT:
					return left.compareTo(right) < 0;

				case LTE:
					return left.compareTo(right) <= 0;

				case GT:
					return left.compareTo(right) > 0;

				case GTE:
					return left.compareTo(right) >= 0;

				default:
					// falls through
				}
			} else {
				throwIncompatibleTypes(lhs, operStr, rhs, Arrays.asList(BigDecimal.class));
				return null;
			}

			throw new CalculationEngineException("Unsupported inequality operation: " + operStr);
		}

		private Object doMultiplicativeOper(ParseTree node) {
			Object lhs = innerEval(node.getChild(0));
			String operStr = node.getChild(1).getText();
			MultiplicativeOper o = MultiplicativeOper.parseValue(operStr);
			Object rhs = innerEval(node.getChild(2));

			if (lhs instanceof BigDecimal && rhs instanceof BigDecimal) {
				BigDecimal left = (BigDecimal) lhs;
				BigDecimal right = (BigDecimal) rhs;
				switch (o) {
				case MULTIPLY:
					return left.multiply(right);

				case DIVIDE:
					return left.divide(right, maxScale, roundingMode);

				case MOD:
					return left.remainder(right);

				default:
					// falls through
				}
			} else {
				throwIncompatibleTypes(lhs, operStr, rhs, Arrays.asList(BigDecimal.class));
				return null;
			}

			throw new CalculationEngineException("Unsupported multiplicative operation: " + operStr);
		}

		private Object doAdditiveOper(ParseTree node) {
			Object lhs = innerEval(node.getChild(0));
			String operStr = node.getChild(1).getText();
			AdditiveOper o = AdditiveOper.parseValue(operStr);
			Object rhs = innerEval(node.getChild(2));

			if (lhs instanceof BigDecimal && rhs instanceof BigDecimal) {
				BigDecimal left = (BigDecimal) lhs;
				BigDecimal right = (BigDecimal) rhs;
				switch (o) {
				case PLUS:
					return left.add(right);

				case MINUS:
					return left.subtract(right);

				default:
					// falls through
				}
			} else {
				throwIncompatibleTypes(lhs, operStr, rhs, Arrays.asList(BigDecimal.class));
				return null;
			}

			throw new CalculationEngineException("Unsupported additive operation: " + operStr);
		}

		private String extractExternConstant(String text) {
			// gets rid of leading '
			String s = text.substring(1);
			// gets rid of training '
			return s.substring(0, s.length() - 1);
		}

		private List<String> getNames(List<Class<?>> desiredTypes) {
			List<String> lst = new ArrayList<>();
			for (Class<?> c : desiredTypes) {
				lst.add(c.getName());
			}
			return lst;
		}

		private void throwIncompatibleTypes(Object lhs, String operStr, Object rhs, List<Class<?>> desiredTypes) {
			throw new CalculationEngineException("Incompatible types for operation [" + lhs + operStr + rhs + "] found."
				+ " 'left' is a " + lhs.getClass().getName() + " while 'right' is a " + rhs.getClass().getName()
				+ ". Both must be the same type out of one of the supported types for this operation: " + getNames(desiredTypes));
		}
	}

	private enum AdditiveOper {
		PLUS("+"),
		MINUS("-");

		String oper;

		private AdditiveOper(String oper) {
			this.oper = oper;
		}

		public String getOper() {
			return oper;
		}

		public static AdditiveOper parseValue(String oper) {
			for (AdditiveOper ao : AdditiveOper.values()) {
				if (ao.getOper().equals(oper)) {
					return ao;
				}
			}
			throw new CalculationEngineException("Unsupported additive operation: " + oper);
		}
	}

	private enum MultiplicativeOper {
		MULTIPLY("*"),
		DIVIDE("/"),
		MOD("mod");

		String oper;

		private MultiplicativeOper(String oper) {
			this.oper = oper;
		}

		public String getOper() {
			return oper;
		}

		public static MultiplicativeOper parseValue(String oper) {
			for (MultiplicativeOper o : MultiplicativeOper.values()) {
				if (o.getOper().equals(oper)) {
					return o;
				}
			}
			throw new CalculationEngineException("Unsupported multiplicative operation: " + oper);
		}
	}

	private enum InequalityOper {
		LT("<"),
		LTE("<="),
		GT(">"),
		GTE(">=");

		String oper;

		private InequalityOper(String oper) {
			this.oper = oper;
		}

		public String getOper() {
			return oper;
		}

		public static InequalityOper parseValue(String oper) {
			for (InequalityOper o : InequalityOper.values()) {
				if (o.getOper().equals(oper)) {
					return o;
				}
			}
			throw new CalculationEngineException("Unsupported inequality operation: " + oper);
		}
	}

	private enum EqualityOper {
		EQ("="),
		NEQ("!=");

		String oper;

		private EqualityOper(String oper) {
			this.oper = oper;
		}

		public String getOper() {
			return oper;
		}

		public static EqualityOper parseValue(String oper) {
			for (EqualityOper o : EqualityOper.values()) {
				if (o.getOper().equals(oper)) {
					return o;
				}
			}
			throw new CalculationEngineException("Unsupported equality operation: " + oper);
		}
	}

	private enum AndOper {
		AND("and");

		String oper;

		private AndOper(String oper) {
			this.oper = oper;
		}

		public String getOper() {
			return oper;
		}

		public static AndOper parseValue(String oper) {
			for (AndOper o : AndOper.values()) {
				if (o.getOper().equals(oper)) {
					return o;
				}
			}
			throw new CalculationEngineException("Unsupported and operation: " + oper);
		}
	}

	private enum OrOper {
		OR("or"),
		XOR("xor");

		String oper;

		private OrOper(String oper) {
			this.oper = oper;
		}

		public String getOper() {
			return oper;
		}

		public static OrOper parseValue(String oper) {
			for (OrOper o : OrOper.values()) {
				if (o.getOper().equals(oper)) {
					return o;
				}
			}
			throw new CalculationEngineException("Unsupported or operation: " + oper);
		}
	}

	// similar to ConsoleErrorListener but gathers the errors in a list
	private static class CalculationEngineErrorListener extends BaseErrorListener {

		private List<String> errors = new ArrayList<>();

		public CalculationEngineErrorListener() {
			// noop
		}

		public List<String> getErrors() {
			return errors;
		}

		@Override
		public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
			errors.add("line " + line + ":" + charPositionInLine + " " + msg);
		}
	}
}
