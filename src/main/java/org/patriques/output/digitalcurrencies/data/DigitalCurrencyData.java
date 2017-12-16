package org.patriques.output.digitalcurrencies.data;

import java.time.LocalDateTime;

/**
 * Representation of json object, i.e:
 * "2014-04-01": {
 *   "1a. open (CNY)": "2813.95451515",
 *   "1b. open (USD)": "453.42760964",
 *   "2a. high (CNY)": "2918.63938797",
 *   "2b. high (USD)": "470.55596761",
 *   "3a. low (CNY)": "2813.95451515",
 *   "3b. low (USD)": "453.42760964",
 *   "4a. close (CNY)": "2859.93706764",
 *   "4b. close (USD)": "461.29103486",
 *   "5. volume": "5628.02114700",
 *   "6. market cap (USD)": "2596155.69911398"
 * }
 */
public class DigitalCurrencyData {

  private final LocalDateTime dateTime;
  private final double openA;
  private final double openB;
  private final double highA;
  private final double highB;
  private final double lowA;
  private final double lowB;
  private final double closeA;
  private final double closeB;
  private final double volume;
  private final double marketCap;

  public DigitalCurrencyData(LocalDateTime dateTime,
                             double openA,
                             double openB,
                             double highA,
                             double highB,
                             double lowA,
                             double lowB,
                             double closeA,
                             double closeB,
                             double volume,
                             double marketCap) {
    this.dateTime = dateTime;
    this.openA = openA;
    this.openB = openB;
    this.highA = highA;
    this.highB = highB;
    this.lowA = lowA;
    this.lowB = lowB;
    this.closeA = closeA;
    this.closeB = closeB;
    this.volume = volume;
    this.marketCap = marketCap;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public double getOpenA() {
    return openA;
  }

  public double getOpenB() {
    return openB;
  }

  public double getHighA() {
    return highA;
  }

  public double getHighB() {
    return highB;
  }

  public double getLowA() {
    return lowA;
  }

  public double getLowB() {
    return lowB;
  }

  public double getCloseA() {
    return closeA;
  }

  public double getCloseB() {
    return closeB;
  }

  public double getVolume() {
    return volume;
  }

  public double getMarketCap() {
    return marketCap;
  }

}
