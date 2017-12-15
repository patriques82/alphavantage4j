package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the average directional movement index rating (ADXR) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class ADXR extends TechnicalIndicatorResponse<IndicatorData> {

  private ADXR(final Map<String, String> metaData,
               final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code ADXR} instance from json.
   *
   * @param json string to parse
   * @return ADXR instance
   */
  public static ADXR from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code ADXR}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<ADXR> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: ADXR";
    }

    @Override
    ADXR resolve(Map<String, String> metaData,
                 Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("ADXR"))
      )));
      return new ADXR(metaData, indicators);
    }
  }
}
