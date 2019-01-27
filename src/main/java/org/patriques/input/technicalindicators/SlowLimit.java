package org.patriques.input.technicalindicators;

import org.patriques.input.ApiParameter;

public class SlowLimit implements ApiParameter {
  private static final long serialVersionUID = -3694510508695701663L;
  private final String limit;

  private SlowLimit(String limit) {
    this.limit = limit;
  }

  public static SlowLimit of(float limit) {
    assert limit > 0.0;
    return new SlowLimit(String.format("%.2f", limit));
  }

  @Override
  public String getKey() {
    return "slowlimit";
  }

  @Override
  public String getValue() {
    return limit;
  }
}

