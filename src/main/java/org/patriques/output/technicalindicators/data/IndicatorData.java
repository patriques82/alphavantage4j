package org.patriques.output.technicalindicators.data;

import java.time.LocalDateTime;

/**
 * Representation of simple indicator json objects, i.e:
 * "2017-12-01 16:00": {
 *   "EMA": "84.0203"
 * }
 */
public class IndicatorData {
  private final LocalDateTime datetime;
  private final double data;

  public IndicatorData(LocalDateTime datetime, double data) {
    this.datetime = datetime;
    this.data = data;
  }

  public LocalDateTime getDateTime() {
    return datetime;
  }

  public double getData() {
    return data;
  }
}
