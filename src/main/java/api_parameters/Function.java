package api_parameters;

public enum Function {
  INTRADAY("TIME_SERIES_INTRADAY"),
  DAILY("TIME_SERIES_DAILY"),
  DAILY_ADJUSTED("TIME_SERIES_DAILY_ADJUSTED"),
  WEEKLY("TIME_SERIES_WEEKLY"),
  WEEKLY_ADJUSTED("TIME_SERIES_WEEKLY_ADJUSTED"),
  MONTHLY("TIME_SERIES_MONTHLY"),
  MONTHLY_ADJUSTED("TIME_SERIES_MONTHLY_ADJUSTED");

  private final String urlParameter;

  private Function(String urlParameter) {
    this.urlParameter = urlParameter;
  }

  public String getUrlParameter() {
    return urlParameter;
  }
}
