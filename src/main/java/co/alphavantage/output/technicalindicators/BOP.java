package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the balance of power (BOP) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class BOP extends TechnicalIndicatorResponse<IndicatorData> {

  private BOP(final Map<String, String> metaData,
              final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code BOP} instance from json.
   *
   * @param json string to parse
   * @return BOP instance
   */
  public static BOP from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code BOP}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<BOP> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: BOP";
    }

    @Override
    BOP resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("BOP"))
      )));
      return new BOP(metaData, indicators);
    }
  }
}
