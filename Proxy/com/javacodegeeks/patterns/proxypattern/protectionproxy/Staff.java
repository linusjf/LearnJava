package com.javacodegeeks.patterns.proxypattern.protectionproxy;

public interface Staff {

  boolean isOwner();

  void setReportGenerator(ReportGeneratorProxy reportGenerator);
}
