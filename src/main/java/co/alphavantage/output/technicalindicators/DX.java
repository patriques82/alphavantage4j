package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the directional movement index (DX)  response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class DX extends TechnicalIndicatorResponse<IndicatorData> {

  private DX(final Map<String, String> metaData,
             final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code DX} instance from json.
   *
   * @param json string to parse
   * @return DX instance
   */
  public static DX from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code DX}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<DX> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: DX";
    }

    @Override
    DX resolve(Map<String, String> metaData,
                   Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("DX"))
      )));
      return new DX(metaData, indicators);
    }
  }
}

