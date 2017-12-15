package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
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
   * @param json string to parse
   * @return ATR instance
   */
  public static ATR from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code ATR}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<ATR> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: ATR";
    }

    @Override
    ATR resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("ATR"))
      )));
      return new ATR(metaData, indicators);
    }
  }
}
