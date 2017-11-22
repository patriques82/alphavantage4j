package response;

public class MetaData {
  private final String info;
  private final String symbol;
  private final String lastRefresh;
  private final String interval;
  private final String outputSize;
  private final String timeZone;

  public MetaData(String info,
                  String symbol,
                  String lastRefresh,
                  String interval,
                  String outputSize,
                  String timeZone) {
    this.info = info;
    this.symbol = symbol;
    this.lastRefresh = lastRefresh;
    this.interval = interval;
    this.outputSize = outputSize;
    this.timeZone = timeZone;
  }

  public String getInfo() {
    return info;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getLastRefresh() {
    return lastRefresh;
  }

  public String getInterval() {
    return interval;
  }

  public String getOutputSize() {
    return outputSize;
  }

  public String getTimeZone() {
    return timeZone;
  }

}
