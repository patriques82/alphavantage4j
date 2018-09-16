package org.patriques.output.technicalindicators;

import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.JsonParser;
import org.patriques.output.technicalindicators.data.HT_SINEData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the Hilbert transform, sine wave (HT_SINE) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class HT_SINE extends TechnicalIndicatorResponse<HT_SINEData> {

  private HT_SINE(final Map<String, String> metaData,
                  final List<HT_SINEData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code HT_SINE} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return HT_SINE instance
   */
  public static HT_SINE from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code HT_SINE}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<HT_SINE> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: HT_SINE";
    }

    @Override
    HT_SINE resolve(Map<String, String> metaData,
                         Map<String, Map<String, String>> indicatorData) {
      List<HT_SINEData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new HT_SINEData(
              resolveDate(key),
              Double.parseDouble(values.get("LEAD SINE")),
              Double.parseDouble(values.get("SINE"))
      )));
      return new HT_SINE(metaData, indicators);
    }
  }
}
