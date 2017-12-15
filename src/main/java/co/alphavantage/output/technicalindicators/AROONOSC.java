package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
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
   * @param json string to parse
   * @return AROONOSC instance
   */
  public static AROONOSC from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code AROONOSC}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<AROONOSC> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: AROONOSC";
    }

    @Override
    AROONOSC resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("AROONOSC"))
      )));
      return new AROONOSC(metaData, indicators);
    }
  }
}
