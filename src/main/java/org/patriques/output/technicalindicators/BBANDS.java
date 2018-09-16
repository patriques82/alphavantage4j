package org.patriques.output.technicalindicators;

import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.JsonParser;
import org.patriques.output.technicalindicators.data.BBANDSData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the Bollinger bands (BBANDS) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class BBANDS extends TechnicalIndicatorResponse<BBANDSData> {

  private BBANDS(final Map<String, String> metaData,
                 final List<BBANDSData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code BBANDS} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return BBANDS instance
   */
  public static BBANDS from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code BBANDS}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<BBANDS> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: BBANDS";
    }

    @Override
    BBANDS resolve(Map<String, String> metaData, Map<String, Map<String, String>> indicatorData) {
      List<BBANDSData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new BBANDSData(
              resolveDate(key),
              Double.parseDouble(values.get("Real Lower Band")),
              Double.parseDouble(values.get("Real Upper Band")),
              Double.parseDouble(values.get("Real Middle Band"))
      )));
      return new BBANDS(metaData, indicators);
    }
  }
}
