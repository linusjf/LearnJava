grammar PropertyFile;
file : prop+ ;
prop : ID '=' STRING '\n' ;
ID   : [a-z]+ ;
STRING : '"' .*? '"' ;
COMMENT :   '#' .*? '\r'? '\n' -> type(NL) ;
