package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the midpoint (MIDPOINT) response from api. MIDPOINT = (highest value + lowest value)/2.
 *
 * @see TechnicalIndicatorResponse
 */
public class MIDPOINT extends TechnicalIndicatorResponse<IndicatorData> {

  private MIDPOINT(final Map<String, String> metaData,
                   final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code MIDPOINT} instance from json.
   *
   * @param json string to parse
   * @return MIDPOINT instance
   */
  public static MIDPOINT from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code MIDPOINT}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<MIDPOINT> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: MIDPOINT";
    }

    @Override
    MIDPOINT resolve(Map<String, String> metaData,
                     Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("MIDPOINT"))
      )));
      return new MIDPOINT(metaData, indicators);
    }
  }
}
