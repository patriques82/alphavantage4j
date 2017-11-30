package input.time_series;

import input.ApiParameter;

/**
 * Interval parameter, i.e interval=15min
 */
public enum Interval implements ApiParameter {
  ONE_MIN("1min"),
  FIVE_MIN("5min"),
  TEN_MIN("10min"),
  FIFTEEN_MIN("15min"),
  THIRTY_MIN("30min"),
  SIXTY_MIN("60min");

  private final String urlParameter;

  Interval(String urlParameter) {
    this.urlParameter = urlParameter;
  }

  @Override
  public String getKey() {
    return "interval";
  }

  @Override
  public String getValue() {
    return urlParameter;
  }
}
