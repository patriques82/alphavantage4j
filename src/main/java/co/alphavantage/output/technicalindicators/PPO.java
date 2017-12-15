package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the percentage price oscillator (PPO) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class PPO extends TechnicalIndicatorResponse<IndicatorData> {

  private PPO(final Map<String, String> metaData,
              final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code PPO} instance from json.
   *
   * @param json string to parse
   * @return PPO instance
   */
  public static PPO from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code PPO}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<PPO> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: PPO";
    }

    @Override
    PPO resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("PPO"))
      )));
      return new PPO(metaData, indicators);
    }
  }
}
