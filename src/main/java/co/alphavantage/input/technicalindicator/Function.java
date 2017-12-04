package co.alphavantage.input.technicalindicator;

import co.alphavantage.input.ApiParameter;

public enum Function implements ApiParameter {
  SMA("SMA");

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
