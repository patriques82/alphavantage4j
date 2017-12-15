package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the Chande momentum oscillator (CMO) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class CMO extends TechnicalIndicatorResponse<IndicatorData> {

  private CMO(final Map<String, String> metaData,
              final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code CMO} instance from json.
   *
   * @param json string to parse
   * @return CMO instance
   */
  public static CMO from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code CMO}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<CMO> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: CMO";
    }

    @Override
    CMO resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("CMO"))
      )));
      return new CMO(metaData, indicators);
    }
  }
}
