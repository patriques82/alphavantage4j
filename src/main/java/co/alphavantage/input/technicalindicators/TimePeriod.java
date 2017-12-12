package co.alphavantage.input.technicalindicators;

import co.alphavantage.input.ApiParameter;

/**
 * Series type parameter for the technical indicators api call.
 */
public class TimePeriod implements ApiParameter {
  private final String urlParameter;

  private TimePeriod(String urlParameter) {
    this.urlParameter = urlParameter;
  }

  public static TimePeriod of(int time) {
    assert time > 0;
    return new TimePeriod(String.format("%d", time));
  }

  @Override
  public String getKey() {
    return "time_period";
  }

  @Override
  public String getValue() {
    return urlParameter;
  }
}
