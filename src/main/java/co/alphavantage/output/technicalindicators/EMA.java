package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of exponential moving average (EMA) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class EMA extends TechnicalIndicatorResponse<IndicatorData> {

  private EMA(final Map<String, String> metaData,
              final List<IndicatorData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code EMA} instance from json.
   *
   * @param json string to parse
   * @return EMA instance
   */
  public static EMA from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code EMA}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<EMA> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: EMA";
    }

    @Override
    EMA resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) throws AlphaVantageException {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("EMA"))
      )));
      return new EMA(metaData, indicators);
    }

  }
}