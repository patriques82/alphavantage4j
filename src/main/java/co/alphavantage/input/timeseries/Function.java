package co.alphavantage.input.timeseries;

import co.alphavantage.input.ApiParameter;

/**
 * Function parameter for the time series api call.
 */
public enum Function implements ApiParameter {
  INTRADAY("TIME_SERIES_INTRADAY"),
  DAILY("TIME_SERIES_DAILY"),
  DAILY_ADJUSTED("TIME_SERIES_DAILY_ADJUSTED"),
  WEEKLY("TIME_SERIES_WEEKLY"),
  WEEKLY_ADJUSTED("TIME_SERIES_WEEKLY_ADJUSTED"),
  MONTHLY("TIME_SERIES_MONTHLY"),
  MONTHLY_ADJUSTED("TIME_SERIES_MONTHLY_ADJUSTED");

  private final String urlParameter;

  Function(String urlParameter) {
    this.urlParameter = urlParameter;
  }

  @Override
  public String getKey() {
    return "function";
  }

  @Override
  public String getValue() {
    return this.urlParameter;
  }
}
