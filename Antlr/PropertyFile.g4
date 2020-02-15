grammar PropertyFile;
file : prop+ 
       | COMMENT+
       ;
prop : ID '=' STRING '\n' ;
ID   : [a-z]+ ;
STRING : '"' .*? '"' ;
COMMENT:   '#' .*? '\r'? '\n' -> skip ;
