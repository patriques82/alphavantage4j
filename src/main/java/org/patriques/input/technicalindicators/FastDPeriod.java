package org.patriques.input.technicalindicators;

import org.patriques.input.ApiParameter;

public class FastDPeriod implements ApiParameter {
  private final String period;

  private FastDPeriod(String period) {
    this.period = period;
  }

  public static FastDPeriod of(int time) {
    assert time > 0;
    return new FastDPeriod(String.format("%d", time));
  }

  @Override
  public String getKey() {
    return "fastdperiod";
  }

  @Override
  public String getValue() {
    return period;
  }
}

