import org.joda.time.DateTime;

public class StockData {
  private final DateTime dateTime;
  private final double open;
  private final double high;
  private final double low;
  private final double close;
  private final long volume;

  public StockData(DateTime dateTime, double open, double high, double low, double close, long volume) {
    this.dateTime = dateTime;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
  }
}
