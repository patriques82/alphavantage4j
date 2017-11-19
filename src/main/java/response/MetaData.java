package response;

import org.joda.time.DateTime;

public class MetaData {

  private final String info;
  private final String symbol;
  private final DateTime lastRefresh;
  private final String timeZone;

  public MetaData(String info, String symbol, DateTime lastRefresh, String timeZone) {
    this.info = info;
    this.symbol = symbol;
    this.lastRefresh = lastRefresh;
    this.timeZone = timeZone;
  }

  public String getInfo() {
    return info;
  }

  public String getSymbol() {
    return symbol;
  }

  public DateTime getLastRefresh() {
    return lastRefresh;
  }

  public String getTimeZone() {
    return timeZone;
  }
}
