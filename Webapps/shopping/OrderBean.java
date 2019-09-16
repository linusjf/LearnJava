package shopping;

import java.util.Date;

public class OrderBean implements java.io.Serializable {
  private String name;
  private String addressLine1, addressLine2, addressLine3;
  private String postCode;
  private String orderItem;
  private int quantity;
  private Date orderDate;

  public String getName() {
    return name;
  }

  public void setName(String nameIn) {
    name = nameIn;
  }

  public String getAddress() {
    return (addressLine1 + "\n" + addressLine2 + "\n" + addressLine3 + "\n"
            + postCode);
  }
  
  public String getAddressLine1() {
    return addressLine1;
  }
  
  public void setAddressLine1(String add1) {
    addressLine1 = add1;
  }
  
  public String getAddressLine2() {
    return addressLine2;
  }
  
  public void setAddressLine2(String add2) {
    addressLine2 = add2;
  }
  
  public String getAddressLine3() {
    return addressLine3;
  }
  
  public void setAddressLine3(String add3) {
    addressLine3 = add3;
  }
  
  public String getPostCode() {
    return postCode;
  }
  
  public void setPostCode(String code) {
    postCode = code;
  }
  
  public String getOrderItem() {
    return orderItem;
  }
  
  public void setOrderItem(String item) {
    orderItem = item;
  }
  
  public int getQuantity() {
    return quantity;
  }
  
  public void setQuantity(int qty) {
    quantity = qty;
  }
}
