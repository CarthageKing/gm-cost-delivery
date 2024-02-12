grammar FhirPathReduced;

// This is based on the following grammar:
//
// https://hl7.org/fhirpath/N1/
// https://hl7.org/fhirpath/N1/fhirpath.g4
//
// It has been modified to remove unnecessary parts and only focus on the computation of numbers

//prog: line (line)*;
//line: ID ( '(' expr ')') ':' expr '\r'? '\n';

expression
        : term                                                      #termExpression
        | expression ('*' | '/' | 'mod') expression         #multiplicativeExpression
        | expression ('+' | '-') expression                   #additiveExpression
        | expression ('<=' | '<' | '>' | '>=') expression           #inequalityExpression
        | expression ('=' | '!=') expression           #equalityExpression
        | expression 'and' expression                               #andExpression
        | expression ('or' | 'xor') expression                      #orExpression
        ;

term
        : invocation                                            #invocationTerm
        | literal                                               #literalTerm
        | externalConstant                                      #externalConstantTerm
        | '(' expression ')'                                    #parenthesizedTerm
        ;

literal
        : ('true' | 'false')                                    #booleanLiteral
        | NUMBER                                                #numberLiteral
        ;

externalConstant
        : '%' ( STRING )
        ;

invocation                          // Terms that can be used after the function/member invocation '.'
        : function                                              #functionInvocation
        ;

function
        : identifier '(' paramList? ')'
        ;

paramList
        : expression (',' expression)*
        ;

identifier
        : IDENTIFIER
        ;


/****************************************************************
    Lexical rules
*****************************************************************/


IDENTIFIER
        : ([A-Za-z] | '_')([A-Za-z0-9] | '_')*            // Added _ to support CQL (FHIR could constrain it out)
        ;

STRING
        : '\'' (ESC | .)*? '\''
        ;

// Also allows leading zeroes now (just like CQL and XSD)
NUMBER
        : [0-9]+('.' [0-9]+)?
        ;

// Pipe whitespace to the HIDDEN channel to support retrieving source text through the parser.
WS
        : [ \r\n\t]+ -> channel(HIDDEN)
        ;

COMMENT
        : '/*' .*? '*/' -> channel(HIDDEN)
        ;

LINE_COMMENT
        : '//' ~[\r\n]* -> channel(HIDDEN)
        ;

fragment ESC
        : '\\' ([`'\\/fnrt] | UNICODE)    // allow \`, \', \\, \/, \f, etc. and \uXXX
        ;

fragment UNICODE
        : 'u' HEX HEX HEX HEX
        ;

fragment HEX
        : [0-9a-fA-F]
        ;
