package co.alphavantage.input.timeseries;

import co.alphavantage.input.ApiParameter;

/**
 * Output size parameter for the time series api call.
 */
public enum OutputSize implements ApiParameter {
  COMPACT("compact"),
  FULL("full");

  private final String urlParameter;

  OutputSize(String urlParameter) {
    this.urlParameter = urlParameter;
  }

  @Override
  public String getKey() {
    return "outputsize";
  }

  @Override
  public String getValue() {
    return urlParameter;
  }
}
