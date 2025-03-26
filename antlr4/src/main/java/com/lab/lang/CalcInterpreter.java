package com.lab.lang;

import com.lab.parser.CalcBaseVisitor;
import com.lab.parser.CalcParser;

public class CalcInterpreter extends CalcBaseVisitor<Integer> {

    // Évaluer une addition ou soustraction
    @Override
    public Integer visitExpr(CalcParser.ExprContext ctx) {
        int result = visit(ctx.term(0));  // Évaluer le premier terme

        // Parcours des termes suivants, qui peuvent être reliés par '+' ou '-'
        for (int i = 1; i < ctx.term().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();  // '+' ou '-'
            int term = visit(ctx.term(i));  // Évaluer le terme suivant
            System.out.println("Result: " + term);
            if (op.equals("+")) {
                result += term;
            } else if (op.equals("-")) {
                result -= term;
            }
        }

        return result;
    }

    // Évaluer une multiplication ou division
    @Override
    public Integer visitTerm(CalcParser.TermContext ctx) {
        int result = visit(ctx.factor(0));  // Évaluer le premier facteur

        // Parcours des facteurs suivants, qui peuvent être reliés par '*' ou '/'
        for (int i = 1; i < ctx.factor().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();  // '*' ou '/'
            int factor = visit(ctx.factor(i));  // Évaluer le facteur suivant
            if (op.equals("*")) {
                result *= factor;
            } else if (op.equals("/")) {
                result /= factor;  // Attention à la division par zéro
            }
        }

        return result;
    }

    // Évaluer un facteur : nombre ou expression entre parenthèses
    @Override
    public Integer visitFactor(CalcParser.FactorContext ctx) {
        if (ctx.NUMBER() != null) {
            return Integer.parseInt(ctx.NUMBER().getText());  // Retourner le nombre
        } else if (ctx.expr() != null) {
            return visit(ctx.expr());  // Évaluer l'expression entre parenthèses
        } else if (ctx.getChild(0).getText().equals("-")) {
            return -visit(ctx.factor());  // Gérer les négatifs
        } else if (ctx.getChild(0).getText().equals("+")) {
            return visit(ctx.factor());  // Gérer les positifs
        }
        return 0;
    }
}
