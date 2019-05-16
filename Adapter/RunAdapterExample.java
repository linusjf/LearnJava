/**
 * Describe class <code>RunAdapterExample</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("PMD.UseUtilityClass")
public class RunAdapterExample {
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    // Object for Xpay
    Xpay xpay = new XpayImpl();
    xpay.setCreditCardNo("4789565874102365");
    xpay.setCustomerName("Max Warner");
    xpay.setCardExpMonth("09");
    xpay.setCardExpYear("25");
    xpay.setCardCvvNo((short) 235);
    xpay.setAmount(2565.23);
    PayD payD = new XpayToPayDAdapter(xpay);
    testPayD(payD);
  }

  private static void testPayD(PayD payD) {
    System.out.println(payD.getCardOwnerName());
    System.out.println(payD.getCustCardNo());
    System.out.println(payD.getCardExpMonthDate());
    System.out.println(payD.getCvvNo());
    System.out.println(payD.getTotalAmount());
  }
}
