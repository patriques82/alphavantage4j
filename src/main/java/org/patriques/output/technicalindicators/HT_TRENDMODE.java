package org.patriques.output.technicalindicators;

import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.JsonParser;
import org.patriques.output.technicalindicators.data.IndicatorData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the Hilbert transform, trend vs cycle mode (HT_TRENDMODE) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class HT_TRENDMODE extends TechnicalIndicatorResponse<IndicatorData> {

  private static final long serialVersionUID = 552813120300611812L;

  private HT_TRENDMODE(final Map<String, String> metaData,
                       final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code HT_TRENDMODE} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return HT_TRENDMODE instance
   */
  public static HT_TRENDMODE from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code HT_TRENDMODE}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<HT_TRENDMODE> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: HT_TRENDMODE";
    }

    @Override
    HT_TRENDMODE resolve(Map<String, String> metaData,
                         Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              resolveDate(key),
              Double.parseDouble(values.get("TRENDMODE"))
      )));
      return new HT_TRENDMODE(metaData, indicators);
    }
  }
}
