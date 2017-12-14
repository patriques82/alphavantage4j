package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of triangular exponential moving average (TRIMA) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class TRIMA extends TechnicalIndicatorResponse<IndicatorData> {

  private TRIMA(final Map<String, String> metaData,
                final List<IndicatorData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code TRIMA} instance from json.
   *
   * @param json string to parse
   * @return TRIMA instance
   */
  public static TRIMA from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code TRIMA}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<TRIMA> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: TRIMA";
    }

    @Override
    TRIMA resolve(Map<String, String> metaData,
                  Map<String, Map<String, String>> indicatorData) throws AlphaVantageException {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("TRIMA"))
      )));
      return new TRIMA(metaData, indicators);
    }

  }
}