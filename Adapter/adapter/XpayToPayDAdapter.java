package adapter;

/**
 * Describe class <code>XpayToPayDAdapter</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("PMD.DataClass")
public class XpayToPayDAdapter implements PayD {
  private String custCardNo;
  private String cardOwnerName;
  private String cardExpMonthDate;
  private Integer cvvNo;
  private Double totalAmount;
  private final Xpay xpay;

  /**
   * Creates a new <code>XpayToPayDAdapter</code> instance.
   *
   * @param xpay a <code>Xpay</code> value
   */
  public XpayToPayDAdapter(Xpay xpay) {
    this.xpay = xpay;
    setProp();
  }

  /**
   * Describe <code>getCustCardNo</code> method here.
   *
   * @return a <code>String</code> value
   */
  @Override
  public String getCustCardNo() {
    return custCardNo;
  }

  /**
   * Describe <code>getCardOwnerName</code> method here.
   *
   * @return a <code>String</code> value
   */
  @Override
  public String getCardOwnerName() {
    return cardOwnerName;
  }

  /**
   * Describe <code>getCardExpMonthDate</code> method here.
   *
   * @return a <code>String</code> value
   */
  @Override
  public String getCardExpMonthDate() {
    return cardExpMonthDate;
  }

  /**
   * Describe <code>getCvvNo</code> method here.
   *
   * @return an <code>Integer</code> value
   */
  @Override
  public Integer getCvvNo() {
    return cvvNo;
  }

  /**
   * Describe <code>getTotalAmount</code> method here.
   *
   * @return a <code>Double</code> value
   */
  @Override
  public Double getTotalAmount() {
    return totalAmount;
  }

  /**
   * Describe <code>setCustCardNo</code> method here.
   *
   * @param custCardNo a <code>String</code> value
   */
  @Override
  public void setCustCardNo(String custCardNo) {
    this.custCardNo = custCardNo;
  }

  /**
   * Describe <code>setCardOwnerName</code> method here.
   *
   * @param cardOwnerName a <code>String</code> value
   */
  @Override
  public void setCardOwnerName(String cardOwnerName) {
    this.cardOwnerName = cardOwnerName;
  }

  /**
   * Describe <code>setCardExpMonthDate</code> method here.
   *
   * @param cardExpMonthDate a <code>String</code> value
   */
  @Override
  public void setCardExpMonthDate(String cardExpMonthDate) {
    this.cardExpMonthDate = cardExpMonthDate;
  }

  /**
   * Describe <code>setCvvNo</code> method here.
   *
   * @param cvvNo an <code>Integer</code> value
   */
  @Override
  public void setCvvNo(Integer cvvNo) {
    this.cvvNo = cvvNo;
  }

  /**
   * Describe <code>setTotalAmount</code> method here.
   *
   * @param totalAmount a <code>Double</code> value
   */
  @Override
  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  private void setProp() {
    setCardOwnerName(this.xpay.getCustomerName());
    setCustCardNo(this.xpay.getCreditCardNo());
    setCardExpMonthDate(this.xpay.getCardExpMonth() + "/" + this.xpay.getCardExpYear());
    setCvvNo(this.xpay.getCardCvvNo().intValue());
    setTotalAmount(this.xpay.getAmount());
  }
}
