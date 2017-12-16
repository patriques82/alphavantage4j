package org.patriques.output.technicalindicators.data;

import java.time.LocalDateTime;

/**
 * Representation of STOCH indicator json objects, i.e:
 * "2017-12-01 16:00":
 * "SlowD": "31.4081",
 * "SlowK": "31.4081"
 * }
 */
public class STOCHDataSlow {
  private final LocalDateTime datetime;
  private final double slowD;
  private final double slowK;

  public STOCHDataSlow(LocalDateTime datetime,
                       double slowD,
                       double slowK) {
    this.datetime = datetime;
    this.slowD = slowD;
    this.slowK = slowK;
  }

  public LocalDateTime getDateTime() {
    return datetime;
  }

  public double getSlowD() {
    return slowD;
  }

  public double getSlowK() {
    return slowK;
  }
}
