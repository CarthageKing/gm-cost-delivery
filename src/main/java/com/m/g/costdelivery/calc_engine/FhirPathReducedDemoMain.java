package com.m.g.costdelivery.calc_engine;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

public class FhirPathReducedDemoMain {

	public static void main(String[] args) throws Exception {
		String expression = "4+5";
		CodePointCharStream charStream = CharStreams.fromString(expression);
		FhirPathReducedLexer lexer = new FhirPathReducedLexer(charStream);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		FhirPathReducedParser parser = new FhirPathReducedParser(tokenStream);
		ParseTree tree = parser.expression();

		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(new WalkerListener(), tree);
	}
}

class WalkerListener implements ParseTreeListener {

	private int indent = 1;

	public WalkerListener() {
		// noop
	}

	@Override
	public void visitTerminal(TerminalNode node) {
		doPrint(node);
	}

	@Override
	public void visitErrorNode(ErrorNode node) {
		// noop
	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		doPrint(ctx);
		indent++;
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		indent--;
		doPrint(ctx);
	}

	private void doPrint(ParseTree tree) {
		System.out.println(String.format("%" + (indent * 4) + "s%s   %s", " ", tree.getClass().getSimpleName(), tree.getText()));
	}
}
