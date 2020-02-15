package lexpr;

// a4 -visitor Expr.g4
    /** Visitor "calculator" */
    public class EvalVisitor extends LExprBaseVisitor<Integer> {
        public Integer visitMult(LExprParser.MultContext ctx) {
            return visit(ctx.e(0)) * visit(ctx.e(1));
        }

        public Integer visitAdd(LExprParser.AddContext ctx) {
            return visit(ctx.e(0)) + visit(ctx.e(1));
        }
        
        public Integer visitSub(LExprParser.SubContext ctx) {
            return visit(ctx.e(0)) - visit(ctx.e(1));
        }

        public Integer visitDiv(LExprParser.DivContext ctx) {
            return visit(ctx.e(0)) / visit(ctx.e(1));
        }
        
        public Integer visitMod(LExprParser.ModContext ctx) {
            return visit(ctx.e(0)) % visit(ctx.e(1));
        }

        public Integer visitInt(LExprParser.IntContext ctx) {
            return Integer.valueOf(ctx.INT().getText());
        }
    }
