package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.FastSTOCHData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the stochastic relative strength index (STOCHRSI) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class STOCHRSI extends TechnicalIndicatorResponse<FastSTOCHData> {

  private STOCHRSI(final Map<String, String> metaData,
                   final List<FastSTOCHData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code STOCHRSI} instance from json.
   *
   * @param json string to parse
   * @return STOCHRSI instance
   */
  public static STOCHRSI from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code STOCHRSI}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<STOCHRSI> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: STOCHRSI";
    }

    @Override
    STOCHRSI resolve(Map<String, String> metaData, Map<String, Map<String, String>> indicatorData) {
      List<FastSTOCHData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new FastSTOCHData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("FastK")),
              Double.parseDouble(values.get("FastD"))
      )));
      return new STOCHRSI(metaData, indicators);
    }
  }
}


