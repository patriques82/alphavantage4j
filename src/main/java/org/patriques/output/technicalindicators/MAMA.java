package org.patriques.output.technicalindicators;

import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.JsonParser;
import org.patriques.output.technicalindicators.data.MAMAData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the MESA adaptive moving average (MAMA) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class MAMA extends TechnicalIndicatorResponse<MAMAData> {

  private MAMA(final Map<String, String> metaData,
               final List<MAMAData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code MAMA} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return MAMA instance
   */
  public static MAMA from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code MAMA}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<MAMA> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: MAMA";
    }

    @Override
    MAMA resolve(Map<String, String> metaData,
                  Map<String, Map<String, String>> indicatorData) throws AlphaVantageException {
      List<MAMAData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new MAMAData(
              resolveDate(key),
              Double.parseDouble(values.get("FAMA")),
              Double.parseDouble(values.get("MAMA"))
      )));
      return new MAMA(metaData, indicators);
    }

  }
}
