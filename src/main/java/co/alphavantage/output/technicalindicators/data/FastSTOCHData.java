package co.alphavantage.output.technicalindicators.data;

import java.time.LocalDateTime;

/**
 * Representation of STOCH indicator json objects, i.e:
 * "2017-12-01 16:00":
 *   "FastD": "25.7924",
 *   "FastK": "18.4211"
 * }
 */
public class FastSTOCHData {
  private final LocalDateTime datetime;
  private final double fastD;
  private final double fastK;

  public FastSTOCHData(LocalDateTime datetime,
                       double fastD,
                       double fastK) {
    this.datetime = datetime;
    this.fastD = fastD;
    this.fastK = fastK;
  }

  public LocalDateTime getDateTime() {
    return datetime;
  }

  public double getFastD() {
    return fastD;
  }

  public double getFastK() {
    return fastK;
  }
}
