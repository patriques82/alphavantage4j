package org.patriques.input.technicalindicators;

import org.patriques.input.ApiParameter;

public class SlowKPeriod implements ApiParameter {
  private final String period;

  private SlowKPeriod(String period) {
    this.period = period;
  }

  public static SlowKPeriod of(int time) {
    assert time > 0;
    return new SlowKPeriod(String.format("%d", time));
  }

  @Override
  public String getKey() {
    return "slowkperiod";
  }

  @Override
  public String getValue() {
    return period;
  }
}
