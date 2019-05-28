package com.javacodegeeks.patterns.builderpattern;

import java.util.Date;

/**
 * Describe class <code>Form</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Form {
  private String firstName;
  private String lastName;
  private String userName;
  private String password;
  private String address;
  private Date dob;
  private String email;
  private String backupEmail;
  private String spouseName;
  private String city;
  private String state;
  private String country;
  private String language;
  private String passwordHint;
  private String securityQuestion;
  private String securityAnswer;

  /**
   * Describe class <code>FormBuilder</code> here.
   *
   */
  public static class FormBuilder {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String address;
    private Date dob;
    private String email;
    private String backupEmail;
    private String spouseName;
    private String city;
    private String state;
    private String country;
    private String language;
    private String passwordHint;
    private String securityQuestion;
    private String securityAnswer;

    /**
     * Creates a new <code>FormBuilder</code> instance.
     *
     * @param firstName a <code>String</code> value
     * @param lastName a <code>String</code> value
     * @param userName a <code>String</code> value
     * @param password a <code>String</code> value
     */
    public FormBuilder(String firstName, String lastName, String userName, String password) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.userName = userName;
      this.password = password;
    }

    /**
     * Describe <code>address</code> method here.
     *
     * @param address a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder address(String address) {
      this.address = address;
      return this;
    }

    /**
     * Describe <code>dob</code> method here.
     *
     * @param dob a <code>Date</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder dob(Date dob) {
      this.dob = dob;
      return this;
    }

    /**
     * Describe <code>email</code> method here.
     *
     * @param email a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder email(String email) {
      this.email = email;
      return this;
    }

    /**
     * Describe <code>backupEmail</code> method here.
     *
     * @param backupEmail a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder backupEmail(String backupEmail) {
      this.backupEmail = backupEmail;
      return this;
    }

    /**
     * Describe <code>spouseName</code> method here.
     *
     * @param spouseName a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder spouseName(String spouseName) {
      this.spouseName = spouseName;
      return this;
    }

    /**
     * Describe <code>city</code> method here.
     *
     * @param city a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder city(String city) {
      this.city = city;
      return this;
    }

    /**
     * Describe <code>state</code> method here.
     *
     * @param state a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder state(String state) {
      this.state = state;
      return this;
    }

    /**
     * Describe <code>country</code> method here.
     *
     * @param country a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder country(String country) {
      this.country = country;
      return this;
    }

    /**
     * Describe <code>language</code> method here.
     *
     * @param language a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder language(String language) {
      this.language = language;
      return this;
    }

    /**
     * Describe <code>passwordHint</code> method here.
     *
     * @param passwordHint a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder passwordHint(String passwordHint) {
      this.passwordHint = passwordHint;
      return this;
    }

    /**
     * Describe <code>securityQuestion</code> method here.
     *
     * @param securityQuestion a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder securityQuestion(String securityQuestion) {
      this.securityQuestion = securityQuestion;
      return this;
    }

    /**
     * Describe <code>securityAnswer</code> method here.
     *
     * @param securityAnswer a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder securityAnswer(String securityAnswer) {
      this.securityAnswer = securityAnswer;
      return this;
    }

    /**
     * Describe <code>build</code> method here.
     *
     * @return a <code>Form</code> value
     */
    public Form build() {
      return new Form(this);
    }
  }

  private Form(FormBuilder formBuilder) {
    this.firstName = formBuilder.firstName;
    this.lastName = formBuilder.lastName;
    this.userName = formBuilder.userName;
    this.password = formBuilder.password;
    this.address = formBuilder.address;
    this.dob = formBuilder.dob;
    this.email = formBuilder.email;
    this.backupEmail = formBuilder.backupEmail;
    this.spouseName = formBuilder.spouseName;
    this.city = formBuilder.city;
    this.state = formBuilder.state;
    this.country = formBuilder.country;
    this.language = formBuilder.language;
    this.passwordHint = formBuilder.passwordHint;
    this.securityQuestion = formBuilder.securityQuestion;
    this.securityAnswer = formBuilder.securityAnswer;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(" First Name: ");
    sb.append(firstName);
    sb.append("\n Last Name: ");
    sb.append(lastName);
    sb.append("\n User Name: ");
    sb.append(userName);
    sb.append("\n Password: ");
    sb.append(password);

    if (this.address != null) {
      sb.append("\n Address: ");
      sb.append(address);
    }
    if (this.dob != null) {
      sb.append("\n DOB: ");
      sb.append(dob);
    }
    if (this.email != null) {
      sb.append("\n Email: ");
      sb.append(email);
    }
    if (this.backupEmail != null) {
      sb.append("\n Backup Email: ");
      sb.append(backupEmail);
    }
    if (this.spouseName != null) {
      sb.append("\n Spouse Name: ");
      sb.append(spouseName);
    }
    if (this.city != null) {
      sb.append("\n City: ");
      sb.append(city);
    }
    if (this.state != null) {
      sb.append("\n State: ");
      sb.append(state);
    }
    if (this.country != null) {
      sb.append("\n Country: ");
      sb.append(country);
    }
    if (this.language != null) {
      sb.append("\n Language: ");
      sb.append(language);
    }
    if (this.passwordHint != null) {
      sb.append("\n Password Hint: ");
      sb.append(passwordHint);
    }
    if (this.securityQuestion != null) {
      sb.append("\n Security Question: ");
      sb.append(securityQuestion);
    }
    if (this.securityAnswer != null) {
      sb.append("\n Security Answer: ");
      sb.append(securityAnswer);
    }

    return sb.toString();
  }

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    Form form = new Form.FormBuilder("Dave", "Carter", "DavCarter", "DAvCaEr123")
                    .passwordHint("MyName")
                    .city("NY")
                    .language("English")
                    .build();
    System.out.println(form);
  }
}
