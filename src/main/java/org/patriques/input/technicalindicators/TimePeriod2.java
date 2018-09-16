package org.patriques.input.technicalindicators;

import org.patriques.input.ApiParameter;

/**
 * Series type parameter for the technical indicators api call.
 */
public class TimePeriod2 implements ApiParameter {
  private final String timePeriod;

  private TimePeriod2(String timePeriod) {
    this.timePeriod = timePeriod;
  }

  public static TimePeriod2 of(int time) {
    assert time > 0;
    return new TimePeriod2(String.format("%d", time));
  }

  @Override
  public String getKey() {
    return "timeperiod2";
  }

  @Override
  public String getValue() {
    return timePeriod;
  }
}
