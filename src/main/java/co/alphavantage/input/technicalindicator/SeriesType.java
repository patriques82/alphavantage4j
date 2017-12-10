package co.alphavantage.input.technicalindicator;

import co.alphavantage.input.ApiParameter;

/**
 * Series type paramater for the technical indicators api call.
 */
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
    return "series_type";
  }

  @Override
  public String getValue() {
    return urlParameter;
  }
}
