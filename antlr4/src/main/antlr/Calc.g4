grammar Calc;

@header {
package com.lab.parser;
}

// Règle de départ de manière explicite
start : expr;

// Parser rules
expr: term (('+' | '-') term)*;
term: factor (('*' | '/') factor)*;
factor: NUMBER | '(' expr ')' | ('-' | '+') factor;

// Lexer rules
NUMBER: [0-9]+ ('.' [0-9]+)?;
WS: [ \t\r\n]+ -> skip;