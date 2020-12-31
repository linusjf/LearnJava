package serial;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

@SuppressWarnings("PMD.DataClass")
public class UserSettings implements Externalizable {

  private static final long serialVersionUID = 1L;

  private Integer fieldOne;
  private String fieldTwo;
  private boolean fieldThree;

  public Integer getFieldOne() {
    return fieldOne;
  }

  public void setFieldOne(Integer fieldOne) {
    this.fieldOne = fieldOne;
  }

  public String getFieldTwo() {
    return fieldTwo;
  }

  public void setFieldTwo(String fieldTwo) {
    this.fieldTwo = fieldTwo;
  }

  public boolean isFieldThree() {
    return fieldThree;
  }

  public void setFieldThree(boolean fieldThree) {
    this.fieldThree = fieldThree;
  }

  @Override
  public String toString() {
    return "UserSettings [fieldOne=" + fieldOne + ", fieldTwo=" + fieldTwo
        + ", fieldThree=" + fieldThree + "]";
  }

  @SuppressWarnings("PMD.SystemPrintln")
  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
    try (ObjectOutputStream oos = (ObjectOutputStream)out;
         XMLEncoder encoder = new XMLEncoder(oos);) {
      encoder.setExceptionListener(
          e -> System.out.println("Exception! :" + e.toString()));
      encoder.writeObject(this);
    }
  }

  @Override
  public void readExternal(ObjectInput in)
      throws IOException, ClassNotFoundException {
    try (ObjectInputStream ois = (ObjectInputStream)in;
         XMLDecoder decoder = new XMLDecoder(ois);) {
      UserSettings decodedSettings = (UserSettings)decoder.readObject();
      setValues(decodedSettings);
    }
  }

  private void setValues(UserSettings settings) {
    this.fieldOne = settings.fieldOne;
    this.fieldTwo = settings.fieldTwo;
    this.fieldThree = settings.fieldThree;
  }
}
