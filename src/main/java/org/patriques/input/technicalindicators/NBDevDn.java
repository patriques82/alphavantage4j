package org.patriques.input.technicalindicators;

import org.patriques.input.ApiParameter;

public class NBDevDn implements ApiParameter {
  private final String multiplier;

  private NBDevDn(String period) {
    this.multiplier = period;
  }

  public static NBDevDn of(int multiplier) {
    assert multiplier > 0;
    return new NBDevDn(String.format("%d", multiplier));
  }

  @Override
  public String getKey() {
    return "nbdevdn";
  }

  @Override
  public String getValue() {
    return multiplier;
  }
}
