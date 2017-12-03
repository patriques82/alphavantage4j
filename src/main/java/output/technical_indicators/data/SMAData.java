package output.technical_indicators.data;

import org.joda.time.DateTime;

public class SMAData {
  private final DateTime dateTime;
  private final double sma;

  public SMAData(DateTime dateTime, double sma) {
    this.dateTime = dateTime;
    this.sma = sma;
  }

  public DateTime getDateTime() {
    return dateTime;
  }

  public double getSma() {
    return sma;
  }
}
