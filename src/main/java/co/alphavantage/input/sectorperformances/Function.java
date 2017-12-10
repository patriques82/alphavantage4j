package co.alphavantage.input.sectorperformances;

import co.alphavantage.input.ApiParameter;

/**
 * Function parameter for the sector api call.
 */
public enum Function implements ApiParameter {
  SECTOR("SECTOR"); // currently only uses one value but this could be extended

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
