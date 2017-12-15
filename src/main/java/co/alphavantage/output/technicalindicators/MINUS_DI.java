package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the minus directional indicator (MINUS_DI) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class MINUS_DI extends TechnicalIndicatorResponse<IndicatorData> {

  private MINUS_DI(final Map<String, String> metaData,
                   final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code MINUS_DI} instance from json.
   *
   * @param json string to parse
   * @return MINUS_DI instance
   */
  public static MINUS_DI from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code MINUS_DI}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<MINUS_DI> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: MINUS_DI";
    }

    @Override
    MINUS_DI resolve(Map<String, String> metaData,
                   Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("MINUS_DI"))
      )));
      return new MINUS_DI(metaData, indicators);
    }
  }
}

