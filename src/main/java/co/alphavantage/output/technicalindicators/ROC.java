package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the rate of change (ROC) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class ROC extends TechnicalIndicatorResponse<IndicatorData> {

  private ROC(final Map<String, String> metaData,
              final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code ROC} instance from json.
   *
   * @param json string to parse
   * @return ROC instance
   */
  public static ROC from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code ROC}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<ROC> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: ROC";
    }

    @Override
    ROC resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("ROC"))
      )));
      return new ROC(metaData, indicators);
    }
  }
}
