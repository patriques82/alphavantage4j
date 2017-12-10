package co.alphavantage.output.technicalindicators.data;

import org.joda.time.DateTime;

/**
 * Representation of json object, i.e:
 * "2017-12-01 16:00": {
 *   "MACD": "84.0203"
 * }
 */
public class MACDData {
  private final DateTime dateTime;
  private final double macd;

  public MACDData(DateTime dateTime, double macd) {
    this.dateTime = dateTime;
    this.macd = macd;
  }

  public DateTime getDateTime() {
    return dateTime;
  }

  public double getMacd() {
    return macd;
  }
}
