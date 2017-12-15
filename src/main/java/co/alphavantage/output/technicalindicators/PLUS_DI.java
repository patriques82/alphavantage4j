package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the plus directional indicator (PLUS_DI) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class PLUS_DI extends TechnicalIndicatorResponse<IndicatorData> {

  private PLUS_DI(final Map<String, String> metaData,
                  final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code PLUS_DI} instance from json.
   *
   * @param json string to parse
   * @return PLUS_DI instance
   */
  public static PLUS_DI from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code PLUS_DI}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<PLUS_DI> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: PLUS_DI";
    }

    @Override
    PLUS_DI resolve(Map<String, String> metaData,
                     Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("PLUS_DI"))
      )));
      return new PLUS_DI(metaData, indicators);
    }
  }
}

