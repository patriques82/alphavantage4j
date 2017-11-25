package parameters;

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
  public String getApiParameterKey() {
    return "interval";
  }

  @Override
  public String getApiParameterValue() {
    return urlParameter;
  }
}
