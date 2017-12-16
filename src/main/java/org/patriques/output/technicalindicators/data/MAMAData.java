package org.patriques.output.technicalindicators.data;

import java.time.LocalDateTime;

/**
 * Representation of MAMA indicator json objects, i.e:
 * "2017-12-01 16:00":
 *   "FAMA": "52.4939",
 *   "MAMA": "80.9751"
 * }
 */
public class MAMAData {
  private final LocalDateTime datetime;
  private final double fama;
  private final double mama;

  public MAMAData(LocalDateTime datetime,
                  double fama,
                  double mama) {
    this.datetime = datetime;
    this.fama = fama;
    this.mama = mama;
  }

  public LocalDateTime getDateTime() {
    return datetime;
  }

  public double getFama() {
    return fama;
  }

  public double getMama() {
    return mama;
  }
}

