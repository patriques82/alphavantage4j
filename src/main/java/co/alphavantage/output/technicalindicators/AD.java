package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the Chaikin A/D line (AD) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class AD extends TechnicalIndicatorResponse<IndicatorData> {

  private AD(final Map<String, String> metaData,
             final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code AD} instance from json.
   *
   * @param json string to parse
   * @return AD instance
   */
  public static AD from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code AD}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<AD> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: Chaikin A/D";
    }

    @Override
    AD resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("Chaikin A/D"))
      )));
      return new AD(metaData, indicators);
    }
  }
}
