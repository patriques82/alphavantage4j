package org.patriques.input.technicalindicators;

import org.patriques.input.ApiParameter;

public class FastPeriod implements ApiParameter {
  private static final long serialVersionUID = -8523816737339734184L;
  private final String period;

  private FastPeriod(String period) {
    this.period = period;
  }

  public static FastPeriod of(int time) {
    assert time > 0;
    return new FastPeriod(String.format("%d", time));
  }

  @Override
  public String getKey() {
    return "fastperiod";
  }

  @Override
  public String getValue() {
    return period;
  }
}
