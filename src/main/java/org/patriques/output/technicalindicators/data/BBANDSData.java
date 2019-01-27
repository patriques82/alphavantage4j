package org.patriques.output.technicalindicators.data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Representation of Bollinger bands indicator json objects, i.e:
 * "2017-12-01 16:00":
 * "Real Lower Band": "89.2034",
 * "Real Upper Band": "119.0966",
 * "Real Middle Band": "104.1500"
 * }
 */
public class BBANDSData implements Serializable {
  private static final long serialVersionUID = 6500139697281506728L;

  private final LocalDateTime datetime;
  private final double lowerBand;
  private final double upperBand;
  private final double midBand;

  public BBANDSData(LocalDateTime datetime,
                    double lowerBand,
                    double upperBand,
                    double midBand) {
    this.datetime = datetime;
    this.lowerBand = lowerBand;
    this.upperBand = upperBand;
    this.midBand = midBand;
  }

  public LocalDateTime getDateTime() {
    return datetime;
  }

  public double getLowerBand() {
    return lowerBand;
  }

  public double getUpperBand() {
    return upperBand;
  }

  public double getMidBand() {
    return midBand;
  }

}
