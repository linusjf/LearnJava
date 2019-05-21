package adapter;

/**
 * Describe interface <code>Xpay</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public interface Xpay {
  /**
   * Describe <code>getCreditCardNo</code> method here.
   *
   * @return a <code>String</code> value
   */
  String getCreditCardNo();

  /**
   * Describe <code>getCustomerName</code> method here.
   *
   * @return a <code>String</code> value
   */
  String getCustomerName();

  /**
   * Describe <code>getCardExpMonth</code> method here.
   *
   * @return a <code>String</code> value
   */
  String getCardExpMonth();

  /**
   * Describe <code>getCardExpYear</code> method here.
   *
   * @return a <code>String</code> value
   */
  String getCardExpYear();

  /**
   * Describe <code>getCardCvvNo</code> method here.
   *
   * @return a <code>Short</code> value
   */
  Short getCardCvvNo();

  /**
   * Describe <code>getAmount</code> method here.
   *
   * @return a <code>Double</code> value
   */
  Double getAmount();

  /**
   * Describe <code>setCreditCardNo</code> method here.
   *
   * @param creditCardNo a <code>String</code> value
   */
  void setCreditCardNo(String creditCardNo);

  /**
   * Describe <code>setCustomerName</code> method here.
   *
   * @param customerName a <code>String</code> value
   */
  void setCustomerName(String customerName);

  /**
   * Describe <code>setCardExpMonth</code> method here.
   *
   * @param cardExpMonth a <code>String</code> value
   */
  void setCardExpMonth(String cardExpMonth);

  /**
   * Describe <code>setCardExpYear</code> method here.
   *
   * @param cardExpYear a <code>String</code> value
   */
  void setCardExpYear(String cardExpYear);

  /**
   * Describe <code>setCardCvvNo</code> method here.
   *
   * @param cardCvvNo a <code>Short</code> value
   */
  void setCardCvvNo(Short cardCvvNo);

  /**
   * Describe <code>setAmount</code> method here.
   *
   * @param amount a <code>Double</code> value
   */
  void setAmount(Double amount);
}
