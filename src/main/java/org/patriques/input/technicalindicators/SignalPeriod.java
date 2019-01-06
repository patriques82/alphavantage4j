package org.patriques.input.technicalindicators;

import org.patriques.input.ApiParameter;

public class SignalPeriod implements ApiParameter {
  private final String period;

  private SignalPeriod(String period) {
    this.period = period;
  }

  public static SignalPeriod of(int time) {
    assert time > 0;
    return new SignalPeriod(String.format("%d", time));
  }

  @Override
  public String getKey() {
    return "signalperiod";
  }

  @Override
  public String getValue() {
    return period;
  }
}

