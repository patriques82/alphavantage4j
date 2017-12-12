package co.alphavantage.output.technicalindicators.data;

import java.time.LocalDateTime;

/**
 * Representation of json object, i.e:
 * "2017-12-01 16:00": {
 *   "SMA": "84.0203"
 * }
 */
public class SMAData {
  private final LocalDateTime dateTime;
  private final double sma;

  public SMAData(LocalDateTime dateTime, double sma) {
    this.dateTime = dateTime;
    this.sma = sma;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public double getSma() {
    return sma;
  }
}
