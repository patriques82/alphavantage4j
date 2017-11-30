package input.technical_indicator;

import input.ApiParameter;

public class TimePeriod implements ApiParameter {
  private final String time;

  private TimePeriod(String time) {
    this.time = time;
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
    return time;
  }
}
