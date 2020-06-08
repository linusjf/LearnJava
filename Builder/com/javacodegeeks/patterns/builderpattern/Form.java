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
    return value == null ? " " + label + ":  " : " " + label + ":  " + value.toString();
  }

  @Override
  public String toString() {
    String ls = System.lineSeparator();
    StringBuilder sb = new StringBuilder(100);
    sb.append(" First Name: ").append(firstName).append("\n Last Name: ").append(lastName).append("\n User Name: ").append(userName).append("\n Password: ").append(password).append(ls).append(toString("Address", address)).append(ls).append(toString("DOB", dob)).append(ls).append(toString("Email", email)).append(ls).append(toString("Backup Email", backupEmail)).append(ls).append(toString("Spouse Name", spouseName)).append(ls).append(toString("City", city)).append(ls).append(toString("State", state)).append(ls).append(toString("Country", country)).append(ls).append(toString("Language", language)).append(ls).append(toString("Password Hint", passwordHint)).append(ls).append(toString("Security Question", securityQuestion)).append(ls).append(toString("Security Answer", securityAnswer));
    return sb.toString();
  }

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(String[] args) {
    Form form = new Form.FormBuilder("Dave", "Carter", "DavCarter", "DAvCaEr123").passwordHint("MyName").city("NY").language("English").build();
    System.out.println(form);
  }


  /**
   * Describe class <code>FormBuilder</code> here.
   */
  @SuppressWarnings({"PMD.TooManyMethods", "PMD.TooManyFields"})
  public static class FormBuilder {
    final String firstName;
    final String lastName;
    final String userName;
    final String password;
    String addr;
    Date dateOfBirth;
    String emailAddress;
    String backupEmailAddress;
    String spouse;
    String cityName;
    String stateName;
    String countryName;
    String lang;
    String hint;
    String securityQuery;
    String securityReply;

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
      this.dateOfBirth = new Date(dob.getTime());
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

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Form)) return false;
    Form other = (Form) o;
    Object this$firstName = this.firstName;
    Object other$firstName = other.firstName;
    if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
    Object this$lastName = this.lastName;
    Object other$lastName = other.lastName;
    if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) return false;
    Object this$userName = this.userName;
    Object other$userName = other.userName;
    if (this$userName == null ? other$userName != null : !this$userName.equals(other$userName)) return false;
    Object this$password = this.password;
    Object other$password = other.password;
    if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
    Object this$address = this.address;
    Object other$address = other.address;
    if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false;
    Object this$dob = this.dob;
    Object other$dob = other.dob;
    if (this$dob == null ? other$dob != null : !this$dob.equals(other$dob)) return false;
    Object this$email = this.email;
    Object other$email = other.email;
    if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
    Object this$backupEmail = this.backupEmail;
    Object other$backupEmail = other.backupEmail;
    if (this$backupEmail == null ? other$backupEmail != null : !this$backupEmail.equals(other$backupEmail)) return false;
    Object this$spouseName = this.spouseName;
    Object other$spouseName = other.spouseName;
    if (this$spouseName == null ? other$spouseName != null : !this$spouseName.equals(other$spouseName)) return false;
    Object this$city = this.city;
    Object other$city = other.city;
    if (this$city == null ? other$city != null : !this$city.equals(other$city)) return false;
    Object this$state = this.state;
    Object other$state = other.state;
    if (this$state == null ? other$state != null : !this$state.equals(other$state)) return false;
    Object this$country = this.country;
    Object other$country = other.country;
    if (this$country == null ? other$country != null : !this$country.equals(other$country)) return false;
    Object this$language = this.language;
    Object other$language = other.language;
    if (this$language == null ? other$language != null : !this$language.equals(other$language)) return false;
    Object this$passwordHint = this.passwordHint;
    Object other$passwordHint = other.passwordHint;
    if (this$passwordHint == null ? other$passwordHint != null : !this$passwordHint.equals(other$passwordHint)) return false;
    Object this$securityQuestion = this.securityQuestion;
    Object other$securityQuestion = other.securityQuestion;
    if (this$securityQuestion == null ? other$securityQuestion != null : !this$securityQuestion.equals(other$securityQuestion)) return false;
    Object this$securityAnswer = this.securityAnswer;
    Object other$securityAnswer = other.securityAnswer;
    if (this$securityAnswer == null ? other$securityAnswer != null : !this$securityAnswer.equals(other$securityAnswer)) return false;
    return true;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $firstName = this.firstName;
    result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
    Object $lastName = this.lastName;
    result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
    Object $userName = this.userName;
    result = result * PRIME + ($userName == null ? 43 : $userName.hashCode());
    Object $password = this.password;
    result = result * PRIME + ($password == null ? 43 : $password.hashCode());
    Object $address = this.address;
    result = result * PRIME + ($address == null ? 43 : $address.hashCode());
    Object $dob = this.dob;
    result = result * PRIME + ($dob == null ? 43 : $dob.hashCode());
    Object $email = this.email;
    result = result * PRIME + ($email == null ? 43 : $email.hashCode());
    Object $backupEmail = this.backupEmail;
    result = result * PRIME + ($backupEmail == null ? 43 : $backupEmail.hashCode());
    Object $spouseName = this.spouseName;
    result = result * PRIME + ($spouseName == null ? 43 : $spouseName.hashCode());
    Object $city = this.city;
    result = result * PRIME + ($city == null ? 43 : $city.hashCode());
    Object $state = this.state;
    result = result * PRIME + ($state == null ? 43 : $state.hashCode());
    Object $country = this.country;
    result = result * PRIME + ($country == null ? 43 : $country.hashCode());
    Object $language = this.language;
    result = result * PRIME + ($language == null ? 43 : $language.hashCode());
    Object $passwordHint = this.passwordHint;
    result = result * PRIME + ($passwordHint == null ? 43 : $passwordHint.hashCode());
    Object $securityQuestion = this.securityQuestion;
    result = result * PRIME + ($securityQuestion == null ? 43 : $securityQuestion.hashCode());
    Object $securityAnswer = this.securityAnswer;
    result = result * PRIME + ($securityAnswer == null ? 43 : $securityAnswer.hashCode());
    return result;
  }
}
