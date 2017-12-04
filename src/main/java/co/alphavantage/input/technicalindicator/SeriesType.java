package co.alphavantage.input.technicalindicator;

import co.alphavantage.input.ApiParameter;

public enum SeriesType implements ApiParameter {
  CLOSE("close"),
  OPEN("open"),
  HIGH("high"),
  LOW("low");

  private final String urlParameter;

  SeriesType(String urlParameter) {
    this.urlParameter = urlParameter;
  }

  @Override
  public String getKey() {
    return null;
  }

  @Override
  public String getValue() {
    return null;
  }
}
