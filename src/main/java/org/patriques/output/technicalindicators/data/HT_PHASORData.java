package org.patriques.output.technicalindicators.data;

import java.time.LocalDateTime;

/**
 * Representation of Hilbert transform, phasor components indicator json objects, i.e:
 * "2000-01-24": {
 *   "PHASE": "-2.5430",
 *   "QUADRATURE": "-10.8250"
 * }
 */
public class HT_PHASORData {
  private final LocalDateTime datetime;
  private final double phase;
  private final double quadrature;

  public HT_PHASORData(LocalDateTime datetime,
                       double phase,
                       double quadrature) {
    this.datetime = datetime;
    this.phase = phase;
    this.quadrature = quadrature;
  }

  public LocalDateTime getDateTime() {
    return datetime;
  }

  public double getPhase() {
    return phase;
  }

  public double getQuadrature() {
    return quadrature;
  }
}

