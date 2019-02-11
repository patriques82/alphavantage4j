package org.patriques.output.technicalindicators;

import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.JsonParser;
import org.patriques.output.technicalindicators.data.IndicatorData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the average directional movement index (ADX) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class ADX extends TechnicalIndicatorResponse<IndicatorData> {

    private static final long serialVersionUID = -917024405238718436L;

    private ADX(final Map<String, String> metaData,
                final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code ADX} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return ADX instance
   */
  public static ADX from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code ADX}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<ADX> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: ADX";
    }

    @Override
    ADX resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              resolveDate(key),
              Double.parseDouble(values.get("ADX"))
      )));
      return new ADX(metaData, indicators);
    }
  }
}
