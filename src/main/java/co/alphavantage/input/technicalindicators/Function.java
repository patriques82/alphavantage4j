package co.alphavantage.input.technicalindicators;

import co.alphavantage.input.ApiParameter;

/**
 * Function parameter for the technical indicators api call.
 */
public enum Function implements ApiParameter {
  SMA("SMA"),
  EMA("EMA"),
  MACD("MACD");

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
