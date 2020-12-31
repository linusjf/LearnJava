package serial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("PMD.DataClass")
public class User implements Serializable {
  private static final long serialVersionUID = 1L;
  private String firstName;
  private String lastName;
  private int accountNumber;
  private Date dateOpened;

  public User(String firstName,
              String lastName,
              int accountNumber,
              Date dateOpened) {
    this();
    this.firstName = firstName;
    this.lastName = lastName;
    this.accountNumber = accountNumber;
    this.dateOpened = (Date)dateOpened.clone();
  }

  public User() {
    // no-args constructor
  }

  private void readObject(ObjectInputStream aInputStream)
      throws ClassNotFoundException, IOException {
    firstName = aInputStream.readUTF();
    lastName = aInputStream.readUTF();
    accountNumber = aInputStream.readInt();
    dateOpened = new Date(aInputStream.readLong());
  }

  private void writeObject(ObjectOutputStream aOutputStream)
      throws IOException {
    aOutputStream.writeUTF(firstName);
    aOutputStream.writeUTF(lastName);
    aOutputStream.writeInt(accountNumber);
    aOutputStream.writeLong(dateOpened.getTime());
  }

  @SuppressWarnings("all")
  public String getFirstName() {
    return this.firstName;
  }

  @SuppressWarnings("all")
  public String getLastName() {
    return this.lastName;
  }

  @SuppressWarnings("all")
  public int getAccountNumber() {
    return this.accountNumber;
  }

  @SuppressWarnings("all")
  public Date getDateOpened() {
    return (Date)this.dateOpened.clone();
  }

  @SuppressWarnings("all")
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @SuppressWarnings("all")
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @SuppressWarnings("all")
  public void setAccountNumber(int accountNumber) {
    this.accountNumber = accountNumber;
  }

  @SuppressWarnings("all")
  public void setDateOpened(Date dateOpened) {
    this.dateOpened = (Date)dateOpened.clone();
  }
}
