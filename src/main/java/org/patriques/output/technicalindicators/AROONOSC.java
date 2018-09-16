package org.patriques.output.technicalindicators;

import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.JsonParser;
import org.patriques.output.technicalindicators.data.IndicatorData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the Aroon oscillator (AROONOSC) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class AROONOSC extends TechnicalIndicatorResponse<IndicatorData> {

  private AROONOSC(final Map<String, String> metaData,
                   final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code AROONOSC} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return AROONOSC instance
   */
  public static AROONOSC from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code AROONOSC}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<AROONOSC> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: AROONOSC";
    }

    @Override
    AROONOSC resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              resolveDate(key),
              Double.parseDouble(values.get("AROONOSC"))
      )));
      return new AROONOSC(metaData, indicators);
    }
  }
}
