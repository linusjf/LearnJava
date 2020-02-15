grammar LExpr;

s : e ;

e : e MOD e 		# Mod
  | e MULT e 		# Mult
  | e DIV e 		# Div
  | e ADD e 		# Add
  | e SUB e 		# Sub
  | INT         # Int
  ;

MOD : '%' ;
MULT: '*' ;
DIV: '/' ;
ADD : '+' ;
SUB: '-' ;
INT : [0-9]+ ;
WS : [ \t\n]+ -> skip ;
