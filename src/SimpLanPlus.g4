grammar SimpLanPlus;

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

prog   : exp                                                            #singleExp
       | (dec)+ (stm)* (exp)?                                           #decStmExp
       ;

dec    : type ID ';'                                                    #idDec
       | type ID '(' ( param ( ',' param)* )? ')' '{' body '}'          #funDec
       ;

param  : type ID ;

body   : (dec)* (stm)* (exp)?
       ;

type   : 'int'
       | 'bool'
       | 'void'
       ;

stm    : ID '=' exp ';'                                                 #idInit
       | ID '(' (exp (',' exp)* )? ')' ';'                              #funStm
       | 'if' '(' cond=exp ')' '{' thenBranch=ifS '}' ('else' '{' elseBranch=ifS '}')?       #ifStm
       ;

exp    :  INTEGER                                                       #intExp
       | ('true' | 'false')                                             #boolExp
       | ID                                                             #varExp
       | not='!' exp                                                        #notExp
       | left=exp (mul='*' | div='/') right=exp                                            #mulDivExp
       | left=exp (sum='+' | sub='-') right=exp                                            #sumSubExp
       | left=exp (gt='>' | lt='<' | gte='>=' | lte='<=' | eq='==') right=exp                       #compareExp
       | left=exp (and='&&' | or='||') right=exp                                          #andOrExp
       | 'if' '(' cond=exp ')' '{' thenBranch=ifE '}' 'else' '{' elseBranch=ifE '}'  #ifExp
       | '(' exp ')'                                                    #parExp
       | ID '(' (exp (',' exp)* )? ')'                                  #funExp
       ;

ifS    : (stm)+ ;
ifE    : (stm)* exp ;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

//Numbers
fragment DIGIT  : '0'..'9';
INTEGER         : DIGIT+;

//IDs
fragment CHAR   : 'a'..'z' |'A'..'Z' ;
ID              : CHAR (CHAR | DIGIT)* ;

//ESCAPE SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip;
LINECOMENTS     : '//' (~('\n'|'\r'))* -> skip;
BLOCKCOMENTS    : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMENTS)* '*/' -> skip;

ERR             : .  -> channel(HIDDEN);