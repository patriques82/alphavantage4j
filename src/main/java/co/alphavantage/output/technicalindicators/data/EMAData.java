package co.alphavantage.output.technicalindicators.data;

import org.joda.time.DateTime;

/**
 * Representation of json object, i.e:
 * "2017-12-01 16:00": {
 *   "EMA": "84.0203"
 * }
 */
public class EMAData {
  private final DateTime datetime;
  private final double ema;

  public EMAData(DateTime datetime, double ema) {
    this.datetime = datetime;
    this.ema = ema;
  }

  public DateTime getDateTime() {
    return datetime;
  }

  public double getEma() {
    return ema;
  }

}
