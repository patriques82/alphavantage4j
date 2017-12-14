package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the relative strength index (RSI) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class RSI extends TechnicalIndicatorResponse<IndicatorData> {

  private RSI(final Map<String, String> metaData,
              final List<IndicatorData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code RSI} instance from json.
   *
   * @param json string to parse
   * @return RSI instance
   */
  public static RSI from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code RSI}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<RSI> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: RSI";
    }

    @Override
    RSI resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) throws AlphaVantageException {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("RSI"))
      )));
      return new RSI(metaData, indicators);
    }
  }
}
