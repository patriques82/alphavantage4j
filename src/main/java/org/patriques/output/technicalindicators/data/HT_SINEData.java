package org.patriques.output.technicalindicators.data;

import java.time.LocalDateTime;

/**
 * Representation of Hilbert transform, sine wave indicator json objects, i.e:
 * "2000-01-24": {
 *   "LEAD SINE": "-0.8497",
 *   "SINE": "-0.9737"
 * }
 */
public class HT_SINEData {
  private final LocalDateTime datetime;
  private final double leadSine;
  private final double sine;

  public HT_SINEData(LocalDateTime datetime,
                   double leadSine,
                   double sine) {
    this.datetime = datetime;
    this.leadSine = leadSine;
    this.sine = sine;
  }

  public LocalDateTime getDateTime() {
    return datetime;
  }

  public double getLeadSine() {
    return leadSine;
  }

  public double getSine() {
    return sine;
  }
}
