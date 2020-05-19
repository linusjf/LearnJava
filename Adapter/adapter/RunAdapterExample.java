package adapter;

import java.util.logging.Logger;

/**
 * Describe class <code>RunAdapterExample</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public enum RunAdapterExample {
  ;
  private static final Logger LOGGER = Logger.getLogger(RunAdapterExample.class.getName());

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    // Object for Xpay
    final Xpay xpay = new XpayImpl();
    xpay.setCreditCardNo("4789565874102365");
    xpay.setCustomerName("Max Warner");
    xpay.setCardExpMonth("09");
    xpay.setCardExpYear("25");
    xpay.setCardCvvNo((short) 235);
    xpay.setAmount(2565.23D);
    final PayD payD = new XpayToPayDAdapter(xpay);
    testPayD(payD);
  }

  private static void testPayD(PayD payD) {
    LOGGER.info(payD.getCardOwnerName());
    LOGGER.info(() -> String.format("%s", payD.getCustCardNo()));
    LOGGER.info(payD.getCardExpMonthDate());
    LOGGER.info(() -> String.format("%d", payD.getCvvNo()));
    LOGGER.info(() -> String.format("%.2f", payD.getTotalAmount()));
  }
}
