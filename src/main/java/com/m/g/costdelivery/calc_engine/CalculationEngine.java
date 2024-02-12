package com.m.g.costdelivery.calc_engine;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

import com.m.g.costdelivery.calc_engine.FhirPathReducedParser.AdditiveExpressionContext;
import com.m.g.costdelivery.calc_engine.FhirPathReducedParser.BooleanLiteralContext;
import com.m.g.costdelivery.calc_engine.FhirPathReducedParser.ExternalConstantTermContext;
import com.m.g.costdelivery.calc_engine.FhirPathReducedParser.FunctionInvocationContext;
import com.m.g.costdelivery.calc_engine.FhirPathReducedParser.NumberLiteralContext;
import com.m.g.costdelivery.exception.CalculationEngineException;

public class CalculationEngine {

	private final ParseTree expressionTree;
	private final List<String> errors;

	// use banker's rounding for internal calculations
	// https://docs.oracle.com/javase/8/docs/api/java/math/RoundingMode.html#HALF_EVEN
	private final MathContext mathCtx = new MathContext(4, RoundingMode.HALF_EVEN);

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
			volume = height.multiply(width, mathCtx).multiply(length, mathCtx);
		}

		public Object doEval() {
			Object result = innerEval(expressionTree);
			if (result instanceof Boolean) {
				return result;
			} else if (result instanceof BigDecimal) {
				return ((BigDecimal) result).stripTrailingZeros();
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
			
			if(node instanceof FunctionInvocationContext) {
				throw new CalculationEngineException("Functions are not yet supported");
			}

			ParseTree inner = node.getChild(0);
			return innerEval(inner);
		}

		private Object doAdditiveOper(ParseTree node) {
			BigDecimal left = (BigDecimal) innerEval(node.getChild(0));
			String operStr = node.getChild(1).getText();
			AdditiveOper ao = AdditiveOper.parseValue(operStr);
			BigDecimal right = (BigDecimal) innerEval(node.getChild(2));

			switch (ao) {
			case PLUS:
				return left.add(right, mathCtx);

			case MINUS:
				return left.subtract(right, mathCtx);

			default:
				// falls through

			}

			throw new CalculationEngineException("Unsupported additive operation: " + operStr);
		}

		private String extractExternConstant(String text) {
			// gets rid of leading '
			String s = text.substring(1);
			// gets rid of training '
			return s.substring(0, s.length() - 1);
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
