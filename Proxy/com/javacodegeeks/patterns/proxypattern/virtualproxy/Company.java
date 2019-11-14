package com.javacodegeeks.patterns.proxypattern.virtualproxy;

/**
 * Describe class <code>Company</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Company {
  private final String companyName;
  private final String companyAddress;
  private final String companyContactNo;
  private final ContactList contactList;

  /**
   * Creates a new <code>Company</code> instance.
   *
   * @param companyName a <code>String</code> value
   * @param companyAddress a <code>String</code> value
   * @param companyContactNo a <code>String</code> value
   * @param contactList a <code>ContactList</code> value
   */
  public Company(
      String companyName, String companyAddress, String companyContactNo, ContactList contactList) {
    this.companyName = companyName;
    this.companyAddress = companyAddress;
    this.companyContactNo = companyContactNo;
    this.contactList = contactList;
    System.out.println("Company object created...");
  }

  /**
   * Describe <code>getCompanyName</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getCompanyName() {
    return companyName;
  }

  /**
   * Describe <code>getCompanyAddress</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getCompanyAddress() {
    return companyAddress;
  }

  /**
   * Describe <code>getCompanyContactNo</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getCompanyContactNo() {
    return companyContactNo;
  }

  /**
   * Describe <code>getContactList</code> method here.
   *
   * @return a <code>ContactList</code> value
   */
  public ContactList getContactList() {
    return contactList;
  }
}
