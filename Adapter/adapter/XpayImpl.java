package adapter;

@SuppressWarnings("PMD.DataClass")
public class XpayImpl implements Xpay {
  private String creditCardNo;
  private String customerName;
  private String cardExpMonth;
  private String cardExpYear;
  private Short cardCvvNo;
  private Double amount;

  @Override
  public String getCreditCardNo() {
    return creditCardNo;
  }

  @Override
  public String getCustomerName() {
    return customerName;
  }

  @Override
  public String getCardExpMonth() {
    return cardExpMonth;
  }

  @Override
  public String getCardExpYear() {
    return cardExpYear;
  }

  @Override
  public Short getCardCvvNo() {
    return cardCvvNo;
  }

  @Override
  public Double getAmount() {
    return amount;
  }

  @Override
  public void setCreditCardNo(String creditCardNo) {
    this.creditCardNo = creditCardNo;
  }

  @Override
  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  @Override
  public void setCardExpMonth(String cardExpMonth) {
    this.cardExpMonth = cardExpMonth;
  }

  @Override
  public void setCardExpYear(String cardExpYear) {
    this.cardExpYear = cardExpYear;
  }

  @Override
  public void setCardCvvNo(Short cardCvvNo) {
    this.cardCvvNo = cardCvvNo;
  }

  @Override
  public void setAmount(Double amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
  return "Card: " + creditCardNo +
    "Name: " + customerName +
  "Expiry: " + cardExpMonth + "/" +cardExpYear +
  "CVV: " + cardCvvNo +
  "Amount: " + amount;
  }
}
