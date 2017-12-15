package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the minus directional movement (MINUS_DM) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class MINUS_DM extends TechnicalIndicatorResponse<IndicatorData> {

  private MINUS_DM(final Map<String, String> metaData,
                   final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code MINUS_DM} instance from json.
   *
   * @param json string to parse
   * @return MINUS_DM instance
   */
  public static MINUS_DM from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code MINUS_DM}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<MINUS_DM> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: MINUS_DM";
    }

    @Override
    MINUS_DM resolve(Map<String, String> metaData,
                    Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("MINUS_DM"))
      )));
      return new MINUS_DM(metaData, indicators);
    }
  }
}

