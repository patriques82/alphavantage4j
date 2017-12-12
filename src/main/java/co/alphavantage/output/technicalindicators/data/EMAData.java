package co.alphavantage.output.technicalindicators.data;

import java.time.LocalDateTime;

/**
 * Representation of json object, i.e:
 * "2017-12-01 16:00": {
 *   "EMA": "84.0203"
 * }
 */
public class EMAData {
  private final LocalDateTime datetime;
  private final double ema;

  public EMAData(LocalDateTime datetime, double ema) {
    this.datetime = datetime;
    this.ema = ema;
  }

  public LocalDateTime getDateTime() {
    return datetime;
  }

  public double getEma() {
    return ema;
  }

}
