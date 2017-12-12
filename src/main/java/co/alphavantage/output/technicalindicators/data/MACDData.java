package co.alphavantage.output.technicalindicators.data;

import java.time.LocalDateTime;

/**
 * Representation of json object, i.e:
 * "2017-12-01 16:00": {
 *   "MACD": "84.0203"
 * }
 */
public class MACDData {
  private final LocalDateTime dateTime;
  private final double macd;

  public MACDData(LocalDateTime dateTime, double macd) {
    this.dateTime = dateTime;
    this.macd = macd;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public double getMacd() {
    return macd;
  }
}
