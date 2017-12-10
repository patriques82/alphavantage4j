package co.alphavantage.output.technicalindicators.data;

import org.joda.time.DateTime;

/**
 * Representation of json object, i.e:
 * "2017-12-01 16:00": {
 *   "SMA": "84.0203"
 * }
 */
public class SMAData {
  private final DateTime dateTime;
  private final double sma;

  public SMAData(DateTime dateTime, double sma) {
    this.dateTime = dateTime;
    this.sma = sma;
  }

  public DateTime getDateTime() {
    return dateTime;
  }

  public double getSma() {
    return sma;
  }
}
