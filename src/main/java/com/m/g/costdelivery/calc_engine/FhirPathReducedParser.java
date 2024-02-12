// Generated from FhirPathReduced.g4 by ANTLR 4.13.1
package com.m.g.costdelivery.calc_engine;

import java.util.List;

import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue" })
public class FhirPathReducedParser extends Parser {
	static {
		RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
		T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, T__16 = 17,
		T__17 = 18, T__18 = 19, T__19 = 20, IDENTIFIER = 21, STRING = 22, NUMBER = 23, WS = 24,
		COMMENT = 25, LINE_COMMENT = 26;
	public static final int RULE_expression = 0, RULE_term = 1, RULE_literal = 2, RULE_externalConstant = 3,
		RULE_invocation = 4, RULE_function = 5, RULE_paramList = 6, RULE_identifier = 7;

	private static String[] makeRuleNames() {
		return new String[] {
			"expression", "term", "literal", "externalConstant", "invocation", "function",
			"paramList", "identifier"
		};
	}

	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'*'", "'/'", "'mod'", "'+'", "'-'", "'<='", "'<'", "'>'", "'>='",
			"'='", "'!='", "'and'", "'or'", "'xor'", "'('", "')'", "'true'", "'false'",
			"'%'", "','"
		};
	}

	private static final String[] _LITERAL_NAMES = makeLiteralNames();

	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, "IDENTIFIER", "STRING",
			"NUMBER", "WS", "COMMENT", "LINE_COMMENT"
		};
	}

	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() {
		return "FhirPathReduced.g4";
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String getSerializedATN() {
		return _serializedATN;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	public FhirPathReducedParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_expression;
		}

		public ExpressionContext() {
		}

		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OrExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public OrExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterOrExpression(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitOrExpression(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AndExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public AndExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterAndExpression(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitAndExpression(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public AdditiveExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterAdditiveExpression(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitAdditiveExpression(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InequalityExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public InequalityExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterInequalityExpression(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitInequalityExpression(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EqualityExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public EqualityExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterEqualityExpression(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitEqualityExpression(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicativeExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public MultiplicativeExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterMultiplicativeExpression(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitMultiplicativeExpression(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermExpressionContext extends ExpressionContext {
		public TermContext term() {
			return getRuleContext(TermContext.class, 0);
		}

		public TermExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterTermExpression(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitTermExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				{
					_localctx = new TermExpressionContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;

					setState(17);
					term();
				}
				_ctx.stop = _input.LT(-1);
				setState(39);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 1, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null)
							triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(37);
							_errHandler.sync(this);
							switch (getInterpreter().adaptivePredict(_input, 0, _ctx)) {
							case 1: {
								_localctx = new MultiplicativeExpressionContext(new ExpressionContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(19);
								if (!(precpred(_ctx, 6)))
									throw new FailedPredicateException(this, "precpred(_ctx, 6)");
								setState(20);
								_la = _input.LA(1);
								if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & 14L) != 0))) {
									_errHandler.recoverInline(this);
								} else {
									if (_input.LA(1) == Token.EOF)
										matchedEOF = true;
									_errHandler.reportMatch(this);
									consume();
								}
								setState(21);
								expression(7);
							}
								break;
							case 2: {
								_localctx = new AdditiveExpressionContext(new ExpressionContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(22);
								if (!(precpred(_ctx, 5)))
									throw new FailedPredicateException(this, "precpred(_ctx, 5)");
								setState(23);
								_la = _input.LA(1);
								if (!(_la == T__3 || _la == T__4)) {
									_errHandler.recoverInline(this);
								} else {
									if (_input.LA(1) == Token.EOF)
										matchedEOF = true;
									_errHandler.reportMatch(this);
									consume();
								}
								setState(24);
								expression(6);
							}
								break;
							case 3: {
								_localctx = new InequalityExpressionContext(new ExpressionContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(25);
								if (!(precpred(_ctx, 4)))
									throw new FailedPredicateException(this, "precpred(_ctx, 4)");
								setState(26);
								_la = _input.LA(1);
								if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & 960L) != 0))) {
									_errHandler.recoverInline(this);
								} else {
									if (_input.LA(1) == Token.EOF)
										matchedEOF = true;
									_errHandler.reportMatch(this);
									consume();
								}
								setState(27);
								expression(5);
							}
								break;
							case 4: {
								_localctx = new EqualityExpressionContext(new ExpressionContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(28);
								if (!(precpred(_ctx, 3)))
									throw new FailedPredicateException(this, "precpred(_ctx, 3)");
								setState(29);
								_la = _input.LA(1);
								if (!(_la == T__9 || _la == T__10)) {
									_errHandler.recoverInline(this);
								} else {
									if (_input.LA(1) == Token.EOF)
										matchedEOF = true;
									_errHandler.reportMatch(this);
									consume();
								}
								setState(30);
								expression(4);
							}
								break;
							case 5: {
								_localctx = new AndExpressionContext(new ExpressionContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(31);
								if (!(precpred(_ctx, 2)))
									throw new FailedPredicateException(this, "precpred(_ctx, 2)");
								setState(32);
								match(T__11);
								setState(33);
								expression(3);
							}
								break;
							case 6: {
								_localctx = new OrExpressionContext(new ExpressionContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(34);
								if (!(precpred(_ctx, 1)))
									throw new FailedPredicateException(this, "precpred(_ctx, 1)");
								setState(35);
								_la = _input.LA(1);
								if (!(_la == T__12 || _la == T__13)) {
									_errHandler.recoverInline(this);
								} else {
									if (_input.LA(1) == Token.EOF)
										matchedEOF = true;
									_errHandler.reportMatch(this);
									consume();
								}
								setState(36);
								expression(2);
							}
								break;
							}
						}
					}
					setState(41);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 1, _ctx);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_term;
		}

		public TermContext() {
		}

		public void copyFrom(TermContext ctx) {
			super.copyFrom(ctx);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExternalConstantTermContext extends TermContext {
		public ExternalConstantContext externalConstant() {
			return getRuleContext(ExternalConstantContext.class, 0);
		}

		public ExternalConstantTermContext(TermContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterExternalConstantTerm(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitExternalConstantTerm(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralTermContext extends TermContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class, 0);
		}

		public LiteralTermContext(TermContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterLiteralTerm(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitLiteralTerm(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesizedTermContext extends TermContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public ParenthesizedTermContext(TermContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterParenthesizedTerm(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitParenthesizedTerm(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InvocationTermContext extends TermContext {
		public InvocationContext invocation() {
			return getRuleContext(InvocationContext.class, 0);
		}

		public InvocationTermContext(TermContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterInvocationTerm(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitInvocationTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_term);
		try {
			setState(49);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				_localctx = new InvocationTermContext(_localctx);
				enterOuterAlt(_localctx, 1); {
				setState(42);
				invocation();
			}
				break;
			case T__16:
			case T__17:
			case NUMBER:
				_localctx = new LiteralTermContext(_localctx);
				enterOuterAlt(_localctx, 2); {
				setState(43);
				literal();
			}
				break;
			case T__18:
				_localctx = new ExternalConstantTermContext(_localctx);
				enterOuterAlt(_localctx, 3); {
				setState(44);
				externalConstant();
			}
				break;
			case T__14:
				_localctx = new ParenthesizedTermContext(_localctx);
				enterOuterAlt(_localctx, 4); {
				setState(45);
				match(T__14);
				setState(46);
				expression(0);
				setState(47);
				match(T__15);
			}
				break;
			default:
				throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_literal;
		}

		public LiteralContext() {
		}

		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BooleanLiteralContext extends LiteralContext {
		public BooleanLiteralContext(LiteralContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterBooleanLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitBooleanLiteral(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberLiteralContext extends LiteralContext {
		public TerminalNode NUMBER() {
			return getToken(FhirPathReducedParser.NUMBER, 0);
		}

		public NumberLiteralContext(LiteralContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterNumberLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitNumberLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_literal);
		int _la;
		try {
			setState(53);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
			case T__17:
				_localctx = new BooleanLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1); {
				setState(51);
				_la = _input.LA(1);
				if (!(_la == T__16 || _la == T__17)) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF)
						matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
			}
				break;
			case NUMBER:
				_localctx = new NumberLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2); {
				setState(52);
				match(NUMBER);
			}
				break;
			default:
				throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExternalConstantContext extends ParserRuleContext {
		public TerminalNode STRING() {
			return getToken(FhirPathReducedParser.STRING, 0);
		}

		public ExternalConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_externalConstant;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterExternalConstant(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitExternalConstant(this);
		}
	}

	public final ExternalConstantContext externalConstant() throws RecognitionException {
		ExternalConstantContext _localctx = new ExternalConstantContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_externalConstant);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(55);
				match(T__18);
				{
					setState(56);
					match(STRING);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InvocationContext extends ParserRuleContext {
		public InvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_invocation;
		}

		public InvocationContext() {
		}

		public void copyFrom(InvocationContext ctx) {
			super.copyFrom(ctx);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionInvocationContext extends InvocationContext {
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class, 0);
		}

		public FunctionInvocationContext(InvocationContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterFunctionInvocation(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitFunctionInvocation(this);
		}
	}

	public final InvocationContext invocation() throws RecognitionException {
		InvocationContext _localctx = new InvocationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_invocation);
		try {
			_localctx = new FunctionInvocationContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
				setState(58);
				function();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class, 0);
		}

		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_function;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterFunction(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitFunction(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(60);
				identifier();
				setState(61);
				match(T__14);
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 11436032L) != 0)) {
					{
						setState(62);
						paramList();
					}
				}

				setState(65);
				match(T__15);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_paramList;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterParamList(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitParamList(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(67);
				expression(0);
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__19) {
					{
						{
							setState(68);
							match(T__19);
							setState(69);
							expression(0);
						}
					}
					setState(74);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() {
			return getToken(FhirPathReducedParser.IDENTIFIER, 0);
		}

		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_identifier;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).enterIdentifier(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FhirPathReducedListener)
				((FhirPathReducedListener) listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(75);
				match(IDENTIFIER);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return expression_sempred((ExpressionContext) _localctx, predIndex);
		}
		return true;
	}

	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		case 5:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN = "\u0004\u0001\u001aN\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002" +
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002" +
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001" +
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001" +
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001" +
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001" +
		"\u0000\u0001\u0000\u0001\u0000\u0005\u0000&\b\u0000\n\u0000\f\u0000)\t" +
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001" +
		"\u0001\u0001\u0001\u0003\u00012\b\u0001\u0001\u0002\u0001\u0002\u0003" +
		"\u00026\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001" +
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005@\b\u0005\u0001" +
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006G\b" +
		"\u0006\n\u0006\f\u0006J\t\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0000" +
		"\u0001\u0000\b\u0000\u0002\u0004\u0006\b\n\f\u000e\u0000\u0006\u0001\u0000" +
		"\u0001\u0003\u0001\u0000\u0004\u0005\u0001\u0000\u0006\t\u0001\u0000\n" +
		"\u000b\u0001\u0000\r\u000e\u0001\u0000\u0011\u0012Q\u0000\u0010\u0001" +
		"\u0000\u0000\u0000\u00021\u0001\u0000\u0000\u0000\u00045\u0001\u0000\u0000" +
		"\u0000\u00067\u0001\u0000\u0000\u0000\b:\u0001\u0000\u0000\u0000\n<\u0001" +
		"\u0000\u0000\u0000\fC\u0001\u0000\u0000\u0000\u000eK\u0001\u0000\u0000" +
		"\u0000\u0010\u0011\u0006\u0000\uffff\uffff\u0000\u0011\u0012\u0003\u0002" +
		"\u0001\u0000\u0012\'\u0001\u0000\u0000\u0000\u0013\u0014\n\u0006\u0000" +
		"\u0000\u0014\u0015\u0007\u0000\u0000\u0000\u0015&\u0003\u0000\u0000\u0007" +
		"\u0016\u0017\n\u0005\u0000\u0000\u0017\u0018\u0007\u0001\u0000\u0000\u0018" +
		"&\u0003\u0000\u0000\u0006\u0019\u001a\n\u0004\u0000\u0000\u001a\u001b" +
		"\u0007\u0002\u0000\u0000\u001b&\u0003\u0000\u0000\u0005\u001c\u001d\n" +
		"\u0003\u0000\u0000\u001d\u001e\u0007\u0003\u0000\u0000\u001e&\u0003\u0000" +
		"\u0000\u0004\u001f \n\u0002\u0000\u0000 !\u0005\f\u0000\u0000!&\u0003" +
		"\u0000\u0000\u0003\"#\n\u0001\u0000\u0000#$\u0007\u0004\u0000\u0000$&" +
		"\u0003\u0000\u0000\u0002%\u0013\u0001\u0000\u0000\u0000%\u0016\u0001\u0000" +
		"\u0000\u0000%\u0019\u0001\u0000\u0000\u0000%\u001c\u0001\u0000\u0000\u0000" +
		"%\u001f\u0001\u0000\u0000\u0000%\"\u0001\u0000\u0000\u0000&)\u0001\u0000" +
		"\u0000\u0000\'%\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000(\u0001" +
		"\u0001\u0000\u0000\u0000)\'\u0001\u0000\u0000\u0000*2\u0003\b\u0004\u0000" +
		"+2\u0003\u0004\u0002\u0000,2\u0003\u0006\u0003\u0000-.\u0005\u000f\u0000" +
		"\u0000./\u0003\u0000\u0000\u0000/0\u0005\u0010\u0000\u000002\u0001\u0000" +
		"\u0000\u00001*\u0001\u0000\u0000\u00001+\u0001\u0000\u0000\u00001,\u0001" +
		"\u0000\u0000\u00001-\u0001\u0000\u0000\u00002\u0003\u0001\u0000\u0000" +
		"\u000036\u0007\u0005\u0000\u000046\u0005\u0017\u0000\u000053\u0001\u0000" +
		"\u0000\u000054\u0001\u0000\u0000\u00006\u0005\u0001\u0000\u0000\u0000" +
		"78\u0005\u0013\u0000\u000089\u0005\u0016\u0000\u00009\u0007\u0001\u0000" +
		"\u0000\u0000:;\u0003\n\u0005\u0000;\t\u0001\u0000\u0000\u0000<=\u0003" +
		"\u000e\u0007\u0000=?\u0005\u000f\u0000\u0000>@\u0003\f\u0006\u0000?>\u0001" +
		"\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000" +
		"AB\u0005\u0010\u0000\u0000B\u000b\u0001\u0000\u0000\u0000CH\u0003\u0000" +
		"\u0000\u0000DE\u0005\u0014\u0000\u0000EG\u0003\u0000\u0000\u0000FD\u0001" +
		"\u0000\u0000\u0000GJ\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000" +
		"HI\u0001\u0000\u0000\u0000I\r\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000" +
		"\u0000KL\u0005\u0015\u0000\u0000L\u000f\u0001\u0000\u0000\u0000\u0006" +
		"%\'15?H";
	public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}