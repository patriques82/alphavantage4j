package org.patriques.input.technicalindicators;

import org.patriques.input.ApiParameter;

/**
 * Series type paramater for the technical indicators api call.
 */
public enum SeriesType implements ApiParameter {
  CLOSE("close"),
  OPEN("open"),
  HIGH("high"),
  LOW("low");

  private final String seriesType;

  SeriesType(String seriesType) {
    this.seriesType = seriesType;
  }

  @Override
  public String getKey() {
    return "series_type";
  }

  @Override
  public String getValue() {
    return seriesType;
  }
}
