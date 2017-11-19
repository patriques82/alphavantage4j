package api_parameters;

public enum Interval {
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

  String getUrlParameter() {
    return urlParameter;
  }

}
