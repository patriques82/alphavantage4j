package co.alphavantage.output.technicalindicators.data;

import org.joda.time.DateTime;

public class EMAData {
  private final DateTime datetime;
  private final double ema;

  public EMAData(DateTime datetime, double ema) {
    this.datetime = datetime;
    this.ema = ema;
  }

  public DateTime getDateTime() {
    return datetime;
  }

  public double getEma() {
    return ema;
  }

}
