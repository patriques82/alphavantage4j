package org.patriques.output.timeseries.data;

import java.time.LocalDateTime;

/**
 * Representation of json object, i.e:
 * "2017-07-06": {
 *   "1. open": "68.2700",
 *   "2. high": "68.7800",
 *   "3. low": "68.1200",
 *   "4. close": "68.5700",
 *   "5. adjusted close": "67.8632",
 *   "6. volume": "20776555",
 *   "7. dividend amount": "0.0000",
 *   "8. split coefficient": "1.0000"
 * }
 */
public class StockData {
  private final LocalDateTime dateTime;
  private final double open;
  private final double high;
  private final double low;
  private final double close;
  private final double adjustedClose;
  private final long volume;
  private final double dividendAmount;
  private final double splitCoefficient;

  public StockData(LocalDateTime dateTime,
                   double open,
                   double high,
                   double low,
                   double close,
                   double adjustedClose,
                   long volume,
                   double dividendAmount,
                   double splitCoefficient) {
    this.dateTime = dateTime;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.adjustedClose = adjustedClose;
    this.volume = volume;
    this.dividendAmount = dividendAmount;
    this.splitCoefficient = splitCoefficient;
  }

  public StockData(LocalDateTime dateTime,
                   double open,
                   double high,
                   double low,
                   double close,
                   double adjustedClose,
                   long volume,
                   double dividendAmount) {
    this.dateTime = dateTime;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.adjustedClose = adjustedClose;
    this.volume = volume;
    this.dividendAmount = dividendAmount;
    this.splitCoefficient = 0;
  }

  public StockData(LocalDateTime dateTime,
                   double open,
                   double high,
                   double low,
                   double close,
                   long volume) {
    this.dateTime = dateTime;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.adjustedClose = 0;
    this.volume = volume;
    this.dividendAmount = 0;
    this.splitCoefficient = 0;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public double getOpen() {
    return open;
  }

  public double getHigh() {
    return high;
  }

  public double getLow() {
    return low;
  }

  public double getClose() {
    return close;
  }

  public double getAdjustedClose() {
    return adjustedClose;
  }

  public long getVolume() {
    return volume;
  }

  public double getDividendAmount() {
    return dividendAmount;
  }

  public double getSplitCoefficient() {
    return splitCoefficient;
  }
}
