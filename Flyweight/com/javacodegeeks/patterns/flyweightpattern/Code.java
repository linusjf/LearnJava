package com.javacodegeeks.patterns.flyweightpattern;

/**
 * Describe class <code>Code</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("PMD.ShortClassName")
public class Code {
  private String sourceCode;

  /**
   * Describe <code>getCode</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getCode() {
    return sourceCode;
  }

  /**
   * Describe <code>setCode</code> method here.
   *
   * @param code a <code>String</code> value
   */
  public void setCode(String code) {
    this.sourceCode = code;
  }
}
