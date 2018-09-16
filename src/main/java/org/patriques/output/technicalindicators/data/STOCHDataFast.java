package org.patriques.output.technicalindicators.data;

import java.time.LocalDateTime;

/**
 * Representation of STOCH indicator json objects, i.e:
 * "2017-12-01 16:00":
 *   "FastD": "25.7924",
 *   "FastK": "18.4211"
 * }
 */
public class STOCHDataFast {
  private final LocalDateTime datetime;
  private final double fastK;
  private final double fastD;

  public STOCHDataFast(LocalDateTime datetime,
                       double fastK,
                       double fastD) {
    this.datetime = datetime;
    this.fastK = fastK;
    this.fastD = fastD;
  }

  public LocalDateTime getDateTime() {
    return datetime;
  }

  public double getFastK() {
    return fastK;
  }

  public double getFastD() {
    return fastD;
  }
}
