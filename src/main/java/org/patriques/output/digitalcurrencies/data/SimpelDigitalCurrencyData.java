package org.patriques.output.digitalcurrencies.data;

import java.time.LocalDateTime;


/**
 * Representation of json object, i.e:
 * "2014-04-01 17:59:59": {
 *   "1a. price (CNY)": "2813.95451515",
 *   "1b. price (USD)": "453.42760964",
 *   "2. volume": "5628.02114700",
 *   "3. market cap (USD)": "2596155.69911398"
 * }
 */
public class SimpelDigitalCurrencyData {

  private final LocalDateTime parse;
  private final double priceA;
  private final double priceB;
  private final double volume;
  private final double marketCap;

  public SimpelDigitalCurrencyData(LocalDateTime parse,
                                   double priceA,
                                   double priceB,
                                   double volume,
                                   double marketCap) {
    this.parse = parse;
    this.priceA = priceA;
    this.priceB = priceB;
    this.volume = volume;
    this.marketCap = marketCap;
  }

  public LocalDateTime getDateTime() {
    return parse;
  }

  public double getPriceA() {
    return priceA;
  }

  public double getPriceB() {
    return priceB;
  }

  public double getVolume() {
    return volume;
  }

  public double getMarketCap() {
    return marketCap;
  }

}
