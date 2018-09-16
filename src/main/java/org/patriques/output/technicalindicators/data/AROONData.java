package org.patriques.output.technicalindicators.data;

import java.time.LocalDateTime;

/**
 * Representation of AROON indicator json objects, i.e:
 * "2000-01-24": {
 *   "Aroon Up": "0.0000",
 *   "Aroon Down": "100.0000"
 * }
 */
public class AROONData {
  private final LocalDateTime datetime;
  private final double aroonUp;
  private final double aroonDown;

  public AROONData(LocalDateTime datetime,
                   double aroonUp,
                   double aroonDown) {
    this.datetime = datetime;
    this.aroonUp = aroonUp;
    this.aroonDown = aroonDown;
  }

  public LocalDateTime getDateTime() {
    return datetime;
  }

  public double getAroonUp() {
    return aroonUp;
  }

  public double getAroonDown() {
    return aroonDown;
  }
}

