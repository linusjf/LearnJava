grammar PropertyFile;
file : prop+ | COMMENT+;
prop : ID '=' STRING '\n' ;
ID   : [a-z]+ ;
STRING : '"' .*? '"' ;
COMMENT : '#' .*? '\r'? '\n' -> type(NL) ;
// Match both UNIX and Windows newlines
NL      : '\r'? '\n' ;
