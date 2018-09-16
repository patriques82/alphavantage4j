package org.patriques.input.technicalindicators;

import org.patriques.input.ApiParameter;

/**
 * Interval parameter for the technical indicators api call.
 */
public enum Interval implements ApiParameter {
  ONE_MIN("1min"),
  FIVE_MIN("5min"),
  FIFTEEN_MIN("15min"),
  THIRTY_MIN("30min"),
  SIXTY_MIN("60min"),
  DAILY("daily"),
  WEEKLY("weekly"),
  MONTHLY("monthly");

  private final String interval;

  Interval(String interval) {
    this.interval = interval;
  }

  @Override
  public String getKey() {
    return "interval";
  }

  @Override
  public String getValue() {
    return interval;
  }
}
