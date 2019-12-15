package com.javacodegeeks.patterns.abstractfactorypattern;

public enum TestAbstractFactoryPattern {
  ;
  static final AbstractParserFactory PARSER_FACTORY_NY = ParserFactoryProducer.getFactory(
    "NYFactory"
  );
  static final AbstractParserFactory PARSER_FACTORY_TW = ParserFactoryProducer.getFactory(
    "TWFactory"
  );

  public static void main(String[] args) {
    XMLParser parser = PARSER_FACTORY_NY.getParserInstance("NYORDER");
    String msg = parser.parse();
    System.out.println(msg);
    System.out.println("************************************");
    XMLParser parser2 = PARSER_FACTORY_TW.getParserInstance("TWFEEDBACK");
    msg = parser2.parse();
    System.out.println(msg);
  }
}
