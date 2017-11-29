package response.data.time_series;

import javax.annotation.Nullable;
import java.util.Optional;

public class MetaData {
  private final String info;
  private final String symbol;
  private final String lastRefresh;
  @Nullable private final String interval;
  @Nullable private final String outputSize;
  private final String timeZone;

  public MetaData(String info,
                  String symbol,
                  String lastRefresh,
                  @Nullable String interval,
                  @Nullable String outputSize,
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

  public Optional<String> getInterval() {
    return Optional.ofNullable(interval);
  }

  public Optional<String> getOutputSize() {
    return Optional.ofNullable(outputSize);
  }

  public String getTimeZone() {
    return timeZone;
  }
}
