/**
 * Describe interface <code>PayD</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public interface PayD {
  /**
   * Describe <code>getCustCardNo</code> method here.
   *
   * @return a <code>String</code> value
   */
  String getCustCardNo();

  /**
   * Describe <code>getCardOwnerName</code> method here.
   *
   * @return a <code>String</code> value
   */
  String getCardOwnerName();

  /**
   * Describe <code>getCardExpMonthDate</code> method here.
   *
   * @return a <code>String</code> value
   */
  String getCardExpMonthDate();

  /**
   * Describe <code>getCvvNo</code> method here.
   *
   * @return an <code>Integer</code> value
   */
  Integer getCvvNo();

  /**
   * Describe <code>getTotalAmount</code> method here.
   *
   * @return a <code>Double</code> value
   */
  Double getTotalAmount();

  /**
   * Describe <code>setCustCardNo</code> method here.
   *
   * @param custCardNo a <code>String</code> value
   */
  void setCustCardNo(String custCardNo);

  /**
   * Describe <code>setCardOwnerName</code> method here.
   *
   * @param cardOwnerName a <code>String</code> value
   */
  void setCardOwnerName(String cardOwnerName);

  /**
   * Describe <code>setCardExpMonthDate</code> method here.
   *
   * @param cardExpMonthDate a <code>String</code> value
   */
  void setCardExpMonthDate(String cardExpMonthDate);

  /**
   * Describe <code>setCvvNo</code> method here.
   *
   * @param cvvNo an <code>Integer</code> value
   */
  void setCvvNo(Integer cvvNo);

  /**
   * Describe <code>setTotalAmount</code> method here.
   *
   * @param totalAmount a <code>Double</code> value
   */
  void setTotalAmount(Double totalAmount);
}
