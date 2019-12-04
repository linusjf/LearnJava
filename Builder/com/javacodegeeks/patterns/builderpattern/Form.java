package com.javacodegeeks.patterns.builderpattern;

import java.util.Date;

/**
 * Describe class <code>Form</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings({"PMD.ShortClassName", "PMD.TooManyFields"})
public final class Form {
  private final String firstName;
  private final String lastName;
  private final String userName;
  private final String password;
  private final String address;
  private final Date dob;
  private final String email;
  private final String backupEmail;
  private final String spouseName;
  private final String city;
  private final String state;
  private final String country;
  private final String language;
  private final String passwordHint;
  private final String securityQuestion;
  private final String securityAnswer;

  private Form(FormBuilder formBuilder) {
    this.firstName = formBuilder.firstName;
    this.lastName = formBuilder.lastName;
    this.userName = formBuilder.userName;
    this.password = formBuilder.password;
    this.address = formBuilder.addr;
    this.dob = formBuilder.dateOfBirth;
    this.email = formBuilder.emailAddress;
    this.backupEmail = formBuilder.backupEmailAddress;
    this.spouseName = formBuilder.spouse;
    this.city = formBuilder.cityName;
    this.state = formBuilder.stateName;
    this.country = formBuilder.countryName;
    this.language = formBuilder.lang;
    this.passwordHint = formBuilder.hint;
    this.securityQuestion = formBuilder.securityQuery;
    this.securityAnswer = formBuilder.securityReply;
  }

  private String toString(String label, Object value) {
    return value == null ? " " + label + ":  "
                         : " " + label + ":  " + value.toString();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(100);
    sb.append(" First Name: ")
        .append(firstName)
        .append("\n Last Name: ")
        .append(lastName)
        .append("\n User Name: ")
        .append(userName)
        .append("\n Password: ")
        .append(password)
        .append(System.lineSeparator())
        .append(toString("Address", address))
        .append(System.lineSeparator())
        .append(toString("DOB", dob))
        .append(System.lineSeparator())
        .append(toString("Email", email))
        .append(System.lineSeparator())
        .append(toString("Backup Email", backupEmail))
        .append(System.lineSeparator())
        .append(toString("Spouse Name", spouseName))
        .append(System.lineSeparator())
        .append(toString("City", city))
        .append(System.lineSeparator())
        .append(toString("State", state))
        .append(System.lineSeparator())
        .append(toString("Country", country))
        .append(System.lineSeparator())
        .append(toString("Language", language))
        .append(System.lineSeparator())
        .append(toString("Password Hint", passwordHint))
        .append(System.lineSeparator())
        .append(toString("Security Question", securityQuestion))
        .append(System.lineSeparator())
        .append(toString("Security Answer", securityAnswer));

    return sb.toString();
  }

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    Form form =
        new Form.FormBuilder("Dave", "Carter", "DavCarter", "DAvCaEr123")
            .passwordHint("MyName")
            .city("NY")
            .language("English")
            .build();
    System.out.println(form);
  }

  /** Describe class <code>FormBuilder</code> here. */
  @SuppressWarnings({"PMD.TooManyMethods", "PMD.TooManyFields"})
  public static class FormBuilder {
    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String password;
    private String addr;
    private Date dateOfBirth;
    private String emailAddress;
    private String backupEmailAddress;
    private String spouse;
    private String cityName;
    private String stateName;
    private String countryName;
    private String lang;
    private String hint;
    private String securityQuery;
    private String securityReply;

    /**
     * Creates a new <code>FormBuilder</code> instance.
     *
     * @param firstName a <code>String</code> value
     * @param lastName a <code>String</code> value
     * @param userName a <code>String</code> value
     * @param password a <code>String</code> value
     */
    public FormBuilder(String firstName,
                       String lastName,
                       String userName,
                       String password) {
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
      this.addr = address;
      return this;
    }

    /**
     * Describe <code>dob</code> method here.
     *
     * @param dob a <code>Date</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder dob(Date dob) {
      this.dateOfBirth = dob;
      return this;
    }

    /**
     * Describe <code>email</code> method here.
     *
     * @param email a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder email(String email) {
      this.emailAddress = email;
      return this;
    }

    /**
     * Describe <code>backupEmail</code> method here.
     *
     * @param backupEmail a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder backupEmail(String backupEmail) {
      this.backupEmailAddress = backupEmail;
      return this;
    }

    /**
     * Describe <code>spouseName</code> method here.
     *
     * @param spouseName a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder spouseName(String spouseName) {
      this.spouse = spouseName;
      return this;
    }

    /**
     * Describe <code>city</code> method here.
     *
     * @param city a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder city(String city) {
      this.cityName = city;
      return this;
    }

    /**
     * Describe <code>state</code> method here.
     *
     * @param state a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder state(String state) {
      this.stateName = state;
      return this;
    }

    /**
     * Describe <code>country</code> method here.
     *
     * @param country a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder country(String country) {
      this.countryName = country;
      return this;
    }

    /**
     * Describe <code>language</code> method here.
     *
     * @param language a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder language(String language) {
      this.lang = language;
      return this;
    }

    /**
     * Describe <code>passwordHint</code> method here.
     *
     * @param passwordHint a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder passwordHint(String passwordHint) {
      this.hint = passwordHint;
      return this;
    }

    /**
     * Describe <code>securityQuestion</code> method here.
     *
     * @param securityQuestion a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder securityQuestion(String securityQuestion) {
      this.securityQuery = securityQuestion;
      return this;
    }

    /**
     * Describe <code>securityAnswer</code> method here.
     *
     * @param securityAnswer a <code>String</code> value
     * @return a <code>FormBuilder</code> value
     */
    public FormBuilder securityAnswer(String securityAnswer) {
      this.securityReply = securityAnswer;
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
}
