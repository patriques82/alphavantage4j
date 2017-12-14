package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.MACDData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of MACD with Controllable MA Type (MACDEXT) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class MACDEXT extends TechnicalIndicatorResponse<MACDData> {

  private MACDEXT(final Map<String, String> metaData,
                  final List<MACDData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code MACDEXT} instance from json.
   *
   * @param json string to parse
   * @return MACDEXT instance
   */
  public static MACDEXT from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code MACDEXT}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<MACDEXT> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: MACDEXT";
    }

    @Override
    MACDEXT resolve(Map<String, String> metaData, Map<String, Map<String, String>> indicatorData) {
      List<MACDData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new MACDData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("MACD_Signal")),
              Double.parseDouble(values.get("MACD_Hist")),
              Double.parseDouble(values.get("MACD"))
      )));
      return new MACDEXT(metaData, indicators);
    }
  }
}
