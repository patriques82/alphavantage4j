package co.alphavantage.input.digitalcurrencies;

import co.alphavantage.input.ApiParameter;

/**
 * Function parameter for the digital and crypto currencies api call.
 */
public enum Function implements ApiParameter {
  INTRADAY("TIME_SERIES_INTRADAY"),
  DAILY("TIME_SERIES_DAILY"),
  WEEKLY("TIME_SERIES_WEEKLY"),
  MONTHLY("TIME_SERIES_MONTHLY");

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
    return urlParameter;
  }
}
