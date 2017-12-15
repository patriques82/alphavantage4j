package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the rate of change ratio (ROCR) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class ROCR extends TechnicalIndicatorResponse<IndicatorDataSimple> {

  private ROCR(final Map<String, String> metaData,
               final List<IndicatorDataSimple> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code ROCR} instance from json.
   *
   * @param json string to parse
   * @return ROCR instance
   */
  public static ROCR from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code ROCR}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<ROCR> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: ROCR";
    }

    @Override
    ROCR resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorDataSimple> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorDataSimple(
              LocalDate.parse(key, SIMPLE_DATE_FORMAT),
              Double.parseDouble(values.get("ROCR"))
      )));
      return new ROCR(metaData, indicators);
    }
  }
}
