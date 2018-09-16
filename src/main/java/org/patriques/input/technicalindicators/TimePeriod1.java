package org.patriques.input.technicalindicators;

import org.patriques.input.ApiParameter;

/**
 * Series type parameter for the technical indicators api call.
 */
public class TimePeriod1 implements ApiParameter {
  private final String timePeriod;

  private TimePeriod1(String timePeriod) {
    this.timePeriod = timePeriod;
  }

  public static TimePeriod1 of(int time) {
    assert time > 0;
    return new TimePeriod1(String.format("%d", time));
  }

  @Override
  public String getKey() {
    return "timeperiod1";
  }

  @Override
  public String getValue() {
    return timePeriod;
  }
}
