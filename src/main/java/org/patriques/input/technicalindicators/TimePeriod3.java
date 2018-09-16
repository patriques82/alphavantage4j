package org.patriques.input.technicalindicators;

import org.patriques.input.ApiParameter;

/**
 * Series type parameter for the technical indicators api call.
 */
public class TimePeriod3 implements ApiParameter {
  private final String timePeriod;

  private TimePeriod3(String timePeriod) {
    this.timePeriod = timePeriod;
  }

  public static TimePeriod3 of(int time) {
    assert time > 0;
    return new TimePeriod3(String.format("%d", time));
  }

  @Override
  public String getKey() {
    return "timeperiod3";
  }

  @Override
  public String getValue() {
    return timePeriod;
  }
}
