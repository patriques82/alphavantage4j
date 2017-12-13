package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of moving average converge/divergence (MACD) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class MACD extends TechnicalIndicatorResponse<IndicatorData> {

  private MACD(final Map<String, String> metaData,
               final List<IndicatorData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code MACD} instance from json.
   *
   * @param json string to parse
   * @return MACD instance
   */
  public static MACD from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code MACD}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<MACD> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: MACD";
    }

    @Override
    MACD resolve(Map<String, String> metaData, Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("MACD"))
      )));
      return new MACD(metaData, indicators);
    }
  }
}
