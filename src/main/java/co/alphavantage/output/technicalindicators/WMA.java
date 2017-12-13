package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Representation of weighted moving average (WMA) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class WMA extends TechnicalIndicatorResponse<IndicatorData> {

  private WMA(final Map<String, String> metaData,
              final List<IndicatorData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code WMA} instance from json.
   *
   * @param json string to parse
   * @return WMA instance
   */
  public static WMA from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code WMA}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<WMA> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: WMA";
    }

    @Override
    WMA resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) throws AlphaVantageException {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("WMA"))
      )));
      return new WMA(metaData, indicators);
    }
  }
}
