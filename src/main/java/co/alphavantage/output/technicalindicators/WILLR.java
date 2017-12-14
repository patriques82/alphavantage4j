package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the Williams' %R (WILLR) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class WILLR extends TechnicalIndicatorResponse<IndicatorData> {

  private WILLR(final Map<String, String> metaData,
                final List<IndicatorData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code WILLR} instance from json.
   *
   * @param json string to parse
   * @return WILLR instance
   */
  public static WILLR from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code WILLR}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<WILLR> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: WILLR";
    }

    @Override
    WILLR resolve(Map<String, String> metaData,
                  Map<String, Map<String, String>> indicatorData) throws AlphaVantageException {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("WILLR"))
      )));
      return new WILLR(metaData, indicators);
    }
  }
}

