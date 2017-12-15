package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the average directional movement index (ADX) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class ADX extends TechnicalIndicatorResponse<IndicatorData> {

  private ADX(final Map<String, String> metaData,
             final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code ADX} instance from json.
   *
   * @param json string to parse
   * @return ADX instance
   */
  public static ADX from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code ADX}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<ADX> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: ADX";
    }

    @Override
    ADX resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("ADX"))
      )));
      return new ADX(metaData, indicators);
    }
  }
}
