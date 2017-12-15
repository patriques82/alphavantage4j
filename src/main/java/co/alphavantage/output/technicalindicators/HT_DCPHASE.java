package co.alphavantage.output.technicalindicators;

import co.alphavantage.input.technicalindicators.Interval;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the Hilbert transform, dominant cycle phase (HT_DCPHASE) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class HT_DCPHASE extends TechnicalIndicatorResponse<IndicatorData> {

  private HT_DCPHASE(final Map<String, String> metaData,
                     final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code HT_DCPHASE} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return HT_DCPHASE instance
   */
  public static HT_DCPHASE from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code HT_DCPHASE}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<HT_DCPHASE> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: HT_DCPHASE";
    }

    @Override
    HT_DCPHASE resolve(Map<String, String> metaData,
                        Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              resolveDate(key),
              Double.parseDouble(values.get("HT_DCPHASE"))
      )));
      return new HT_DCPHASE(metaData, indicators);
    }
  }
}
