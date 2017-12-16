package org.patriques.output.technicalindicators.data;

import java.time.LocalDateTime;

/**
 * Representation of MAMA indicator json objects, i.e:
 * "2017-12-01 16:00":
 *   "MACD_Signal": "-0.0265",
 *   "MACD_Hist": "-0.0074",
 *   "MACD": "-0.0339"
 * }
 */
public class MACDData {
  private final LocalDateTime datetime;
  private final double signal;
  private final double hist;
  private final double macd;

  public MACDData(LocalDateTime datetime,
                  double signal,
                  double hist,
                  double macd) {
    this.datetime = datetime;
    this.signal = signal;
    this.hist = hist;
    this.macd = macd;
  }

  public LocalDateTime getDateTime() {
    return datetime;
  }

  public double getSignal() {
    return signal;
  }

  public double getHist() {
    return hist;
  }

  public double getMacd() {
    return macd;
  }

}
