package org.patriques.output.technicalindicators;

import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.JsonParser;
import org.patriques.output.technicalindicators.data.IndicatorData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the minus directional indicator (MINUS_DI) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class MINUS_DI extends TechnicalIndicatorResponse<IndicatorData> {

    private static final long serialVersionUID = 275267865975785157L;

    private MINUS_DI(final Map<String, String> metaData,
                     final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code MINUS_DI} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return MINUS_DI instance
   */
  public static MINUS_DI from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code MINUS_DI}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<MINUS_DI> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: MINUS_DI";
    }

    @Override
    MINUS_DI resolve(Map<String, String> metaData,
                   Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              resolveDate(key),
              Double.parseDouble(values.get("MINUS_DI"))
      )));
      return new MINUS_DI(metaData, indicators);
    }
  }
}

