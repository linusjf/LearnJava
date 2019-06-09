package com.javacodegeeks.patterns.abstractfactorypattern;

public interface AbstractParserFactory {

  XMLParser getParserInstance(String parserType);
}
