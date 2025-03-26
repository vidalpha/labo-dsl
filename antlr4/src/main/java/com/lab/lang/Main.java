package com.lab.lang;

import com.lab.parser.CalcLexer;
import com.lab.parser.CalcParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        // Exemple d'expressions
        String expression = "3 + 5 * (10 - 2)";

        // Créer le flux d'entrée pour le lexer
        ANTLRInputStream input = new ANTLRInputStream(expression);

        // Créer le lexer pour scanner l'expression
        CalcLexer lexer = new CalcLexer(input);

        // Créer un stream de tokens à partir du lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Créer le parseur avec le flux de tokens
        CalcParser parser = new CalcParser(tokens);

        // Analyser l'expression avec la règle 'start' (qui est la règle 'expr')
        ParseTree tree = parser.start(); // point d'entrée de l'analyse (la règle 'start' définit l'expression)

        // Interpréter l'arbre syntaxique avec un visiteur
        CalcInterpreter interpreter = new CalcInterpreter();
        int result = interpreter.visit(tree); // Utilisation du visitor pour obtenir le résultat

        // Affichage du résultat
        System.out.println("Result: " + result);
    }
}