package java6;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;

/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/
public class ExtractInterfaceListener extends Java6BaseListener {
    Java6Parser parser;

    public ExtractInterfaceListener(Java6Parser parser) {this.parser = parser;}
    
    /** Listen to matches of classDeclaration */
    @Override
    public void enterClassDeclaration(Java6Parser.ClassDeclarationContext ctx){
      System.out.println();
      System.out.println("interface I"+ctx.Identifier()+" {");
    }
    
	@Override 
  public void enterPackageDeclaration(Java6Parser.PackageDeclarationContext ctx) { 
System.out.println(parser.getTokenStream().getText(ctx));
  }


	@Override 
  public void exitPackageDeclaration(Java6Parser.PackageDeclarationContext ctx) {
    System.out.println();
  }

	@Override 
  public void enterImportDeclaration(Java6Parser.ImportDeclarationContext ctx) {
System.out.println(parser.getTokenStream().getText(ctx));
  }

	@Override 
  public void exitImportDeclaration(Java6Parser.ImportDeclarationContext ctx) { 

  }

    @Override
    public void exitClassDeclaration(Java6Parser.ClassDeclarationContext ctx) {
        System.out.println("}");
    }

    /** Listen to matches of methodDeclaration */
    @Override
    public void enterMethodDeclaration(
        Java6Parser.MethodDeclarationContext ctx
    )
    {
        // need parser to get tokens
        TokenStream tokens = parser.getTokenStream();
        String type = "void";
        if ( ctx.type()!=null ) {
            type = tokens.getText(ctx.type());
        }
        String args = tokens.getText(ctx.formalParameters());
        System.out.println("\t"+type+" "+ctx.Identifier()+args+";");
    }
}
