package org.patriques.input.technicalindicators;

import org.patriques.input.ApiParameter;

/**
 * Series type parameter for the technical indicators api call.
 */
public class TimePeriod implements ApiParameter {
  private final String timePeriod;

  private TimePeriod(String timePeriod) {
    this.timePeriod = timePeriod;
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
    return timePeriod;
  }
}
