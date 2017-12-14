package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the triple exponential moving average (T3) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class T3 extends TechnicalIndicatorResponse<IndicatorData> {

  private T3(final Map<String, String> metaData,
             final List<IndicatorData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@codeT3} instance from json.
   *
   * @param json string to parse
   * @return T3 instance
   */
  public static T3 from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code T3}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<T3> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: T3";
    }

    @Override
    T3 resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("T3"))
      )));
      return new T3(metaData, indicators);
    }
  }
}

