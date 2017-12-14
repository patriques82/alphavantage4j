package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.MAMAData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the MESA adaptive moving average (MAMA) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class MAMA extends TechnicalIndicatorResponse<MAMAData> {

  private MAMA(final Map<String, String> metaData,
               final List<MAMAData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code MAMA} instance from json.
   *
   * @param json string to parse
   * @return MAMA instance
   */
  public static MAMA from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code MAMA}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<MAMA> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: MAMA";
    }

    @Override
    MAMA resolve(Map<String, String> metaData,
                  Map<String, Map<String, String>> indicatorData) throws AlphaVantageException {
      List<MAMAData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new MAMAData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("FAMA")),
              Double.parseDouble(values.get("MAMA"))
      )));
      return new MAMA(metaData, indicators);
    }

  }
}
