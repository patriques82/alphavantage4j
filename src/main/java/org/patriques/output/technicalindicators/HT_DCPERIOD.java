package org.patriques.output.technicalindicators;

import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.JsonParser;
import org.patriques.output.technicalindicators.data.IndicatorData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the Hilbert transform, dominant cycle period (HT_DCPERIOD) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class HT_DCPERIOD extends TechnicalIndicatorResponse<IndicatorData> {

  private HT_DCPERIOD(final Map<String, String> metaData,
                      final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code HT_DCPERIOD} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return HT_DCPERIOD instance
   */
  public static HT_DCPERIOD from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code HT_DCPERIOD}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<HT_DCPERIOD> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: HT_DCPERIOD";
    }

    @Override
    HT_DCPERIOD resolve(Map<String, String> metaData,
                         Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              resolveDate(key),
              Double.parseDouble(values.get("DCPERIOD"))
      )));
      return new HT_DCPERIOD(metaData, indicators);
    }
  }
}
