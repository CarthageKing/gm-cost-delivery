// Generated from FhirPathReduced.g4 by ANTLR 4.13.1
package com.m.g.costdelivery.calc_engine;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FhirPathReducedParser}.
 */
public interface FhirPathReducedListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link FhirPathReducedParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(FhirPathReducedParser.OrExpressionContext ctx);

	/**
	 * Exit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link FhirPathReducedParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(FhirPathReducedParser.OrExpressionContext ctx);

	/**
	 * Enter a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link FhirPathReducedParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(FhirPathReducedParser.AndExpressionContext ctx);

	/**
	 * Exit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link FhirPathReducedParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(FhirPathReducedParser.AndExpressionContext ctx);

	/**
	 * Enter a parse tree produced by the {@code additiveExpression}
	 * labeled alternative in {@link FhirPathReducedParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(FhirPathReducedParser.AdditiveExpressionContext ctx);

	/**
	 * Exit a parse tree produced by the {@code additiveExpression}
	 * labeled alternative in {@link FhirPathReducedParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(FhirPathReducedParser.AdditiveExpressionContext ctx);

	/**
	 * Enter a parse tree produced by the {@code inequalityExpression}
	 * labeled alternative in {@link FhirPathReducedParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInequalityExpression(FhirPathReducedParser.InequalityExpressionContext ctx);

	/**
	 * Exit a parse tree produced by the {@code inequalityExpression}
	 * labeled alternative in {@link FhirPathReducedParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInequalityExpression(FhirPathReducedParser.InequalityExpressionContext ctx);

	/**
	 * Enter a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link FhirPathReducedParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(FhirPathReducedParser.EqualityExpressionContext ctx);

	/**
	 * Exit a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link FhirPathReducedParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(FhirPathReducedParser.EqualityExpressionContext ctx);

	/**
	 * Enter a parse tree produced by the {@code multiplicativeExpression}
	 * labeled alternative in {@link FhirPathReducedParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(FhirPathReducedParser.MultiplicativeExpressionContext ctx);

	/**
	 * Exit a parse tree produced by the {@code multiplicativeExpression}
	 * labeled alternative in {@link FhirPathReducedParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(FhirPathReducedParser.MultiplicativeExpressionContext ctx);

	/**
	 * Enter a parse tree produced by the {@code termExpression}
	 * labeled alternative in {@link FhirPathReducedParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTermExpression(FhirPathReducedParser.TermExpressionContext ctx);

	/**
	 * Exit a parse tree produced by the {@code termExpression}
	 * labeled alternative in {@link FhirPathReducedParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTermExpression(FhirPathReducedParser.TermExpressionContext ctx);

	/**
	 * Enter a parse tree produced by the {@code invocationTerm}
	 * labeled alternative in {@link FhirPathReducedParser#term}.
	 * @param ctx the parse tree
	 */
	void enterInvocationTerm(FhirPathReducedParser.InvocationTermContext ctx);

	/**
	 * Exit a parse tree produced by the {@code invocationTerm}
	 * labeled alternative in {@link FhirPathReducedParser#term}.
	 * @param ctx the parse tree
	 */
	void exitInvocationTerm(FhirPathReducedParser.InvocationTermContext ctx);

	/**
	 * Enter a parse tree produced by the {@code literalTerm}
	 * labeled alternative in {@link FhirPathReducedParser#term}.
	 * @param ctx the parse tree
	 */
	void enterLiteralTerm(FhirPathReducedParser.LiteralTermContext ctx);

	/**
	 * Exit a parse tree produced by the {@code literalTerm}
	 * labeled alternative in {@link FhirPathReducedParser#term}.
	 * @param ctx the parse tree
	 */
	void exitLiteralTerm(FhirPathReducedParser.LiteralTermContext ctx);

	/**
	 * Enter a parse tree produced by the {@code externalConstantTerm}
	 * labeled alternative in {@link FhirPathReducedParser#term}.
	 * @param ctx the parse tree
	 */
	void enterExternalConstantTerm(FhirPathReducedParser.ExternalConstantTermContext ctx);

	/**
	 * Exit a parse tree produced by the {@code externalConstantTerm}
	 * labeled alternative in {@link FhirPathReducedParser#term}.
	 * @param ctx the parse tree
	 */
	void exitExternalConstantTerm(FhirPathReducedParser.ExternalConstantTermContext ctx);

	/**
	 * Enter a parse tree produced by the {@code parenthesizedTerm}
	 * labeled alternative in {@link FhirPathReducedParser#term}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedTerm(FhirPathReducedParser.ParenthesizedTermContext ctx);

	/**
	 * Exit a parse tree produced by the {@code parenthesizedTerm}
	 * labeled alternative in {@link FhirPathReducedParser#term}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedTerm(FhirPathReducedParser.ParenthesizedTermContext ctx);

	/**
	 * Enter a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link FhirPathReducedParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(FhirPathReducedParser.BooleanLiteralContext ctx);

	/**
	 * Exit a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link FhirPathReducedParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(FhirPathReducedParser.BooleanLiteralContext ctx);

	/**
	 * Enter a parse tree produced by the {@code numberLiteral}
	 * labeled alternative in {@link FhirPathReducedParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterNumberLiteral(FhirPathReducedParser.NumberLiteralContext ctx);

	/**
	 * Exit a parse tree produced by the {@code numberLiteral}
	 * labeled alternative in {@link FhirPathReducedParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitNumberLiteral(FhirPathReducedParser.NumberLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link FhirPathReducedParser#externalConstant}.
	 * @param ctx the parse tree
	 */
	void enterExternalConstant(FhirPathReducedParser.ExternalConstantContext ctx);

	/**
	 * Exit a parse tree produced by {@link FhirPathReducedParser#externalConstant}.
	 * @param ctx the parse tree
	 */
	void exitExternalConstant(FhirPathReducedParser.ExternalConstantContext ctx);

	/**
	 * Enter a parse tree produced by the {@code functionInvocation}
	 * labeled alternative in {@link FhirPathReducedParser#invocation}.
	 * @param ctx the parse tree
	 */
	void enterFunctionInvocation(FhirPathReducedParser.FunctionInvocationContext ctx);

	/**
	 * Exit a parse tree produced by the {@code functionInvocation}
	 * labeled alternative in {@link FhirPathReducedParser#invocation}.
	 * @param ctx the parse tree
	 */
	void exitFunctionInvocation(FhirPathReducedParser.FunctionInvocationContext ctx);

	/**
	 * Enter a parse tree produced by {@link FhirPathReducedParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(FhirPathReducedParser.FunctionContext ctx);

	/**
	 * Exit a parse tree produced by {@link FhirPathReducedParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(FhirPathReducedParser.FunctionContext ctx);

	/**
	 * Enter a parse tree produced by {@link FhirPathReducedParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(FhirPathReducedParser.ParamListContext ctx);

	/**
	 * Exit a parse tree produced by {@link FhirPathReducedParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(FhirPathReducedParser.ParamListContext ctx);

	/**
	 * Enter a parse tree produced by {@link FhirPathReducedParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(FhirPathReducedParser.IdentifierContext ctx);

	/**
	 * Exit a parse tree produced by {@link FhirPathReducedParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(FhirPathReducedParser.IdentifierContext ctx);
}