package org.patriques.output.technicalindicators;

import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.JsonParser;
import org.patriques.output.technicalindicators.data.IndicatorData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the average true range (ATR) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class ATR extends TechnicalIndicatorResponse<IndicatorData> {

  private ATR(final Map<String, String> metaData,
              final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code ATR} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return ATR instance
   */
  public static ATR from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code ATR}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<ATR> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: ATR";
    }

    @Override
    ATR resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              resolveDate(key),
              Double.parseDouble(values.get("ATR"))
      )));
      return new ATR(metaData, indicators);
    }
  }
}
