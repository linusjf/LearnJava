package java6;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/
public class InsertSerialIDListener extends Java6BaseListener {
    TokenStreamRewriter rewriter;

    public InsertSerialIDListener(TokenStream tokens) {
        rewriter = new TokenStreamRewriter(tokens);
    }

    @Override
    public void enterClassBody(Java6Parser.ClassBodyContext ctx) {
        String field = "\n\tpublic static final long serialVersionUID = 1L;\n";
        rewriter.insertAfter(ctx.start, field);
    }
}
