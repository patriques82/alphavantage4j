package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of triple exponential moving average (TEMA) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class TEMA extends TechnicalIndicatorResponse<IndicatorData> {

  private TEMA(final Map<String, String> metaData,
               final List<IndicatorData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code TEMA} instance from json.
   *
   * @param json string to parse
   * @return TEMA instance
   */
  public static TEMA from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code TEMA}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<TEMA> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: TEMA";
    }

    @Override
    TEMA resolve(Map<String, String> metaData,
                 Map<String, Map<String, String>> indicatorData) throws AlphaVantageException {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("TEMA"))
      )));
      return new TEMA(metaData, indicators);
    }
  }
}
