package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the midpoint price (MIDPRICE) response from api. MIDPRICE = (highest high + lowest low)/2.
 *
 * @see TechnicalIndicatorResponse
 */
public class MIDPRICE extends TechnicalIndicatorResponse<IndicatorData> {

  private MIDPRICE(final Map<String, String> metaData,
                   final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code MIDPRICE} instance from json.
   *
   * @param json string to parse
   * @return MIDPRICE instance
   */
  public static MIDPRICE from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code MIDPRICE}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<MIDPRICE> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: MIDPRICE";
    }

    @Override
    MIDPRICE resolve(Map<String, String> metaData,
                     Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("MIDPRICE"))
      )));
      return new MIDPRICE(metaData, indicators);
    }
  }
}
