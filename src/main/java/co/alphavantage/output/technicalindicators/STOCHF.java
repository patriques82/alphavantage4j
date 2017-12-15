package co.alphavantage.output.technicalindicators;

import co.alphavantage.input.technicalindicators.Interval;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.FastSTOCHData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the stochastic fast (STOCHF) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class STOCHF extends TechnicalIndicatorResponse<FastSTOCHData> {

  private STOCHF(final Map<String, String> metaData,
                final List<FastSTOCHData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code STOCHF} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return STOCHF instance
   */
  public static STOCHF from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code STOCHF}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<STOCHF> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: STOCHF";
    }

    @Override
    STOCHF resolve(Map<String, String> metaData, Map<String, Map<String, String>> indicatorData) {
      List<FastSTOCHData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new FastSTOCHData(
              resolveDate(key),
              Double.parseDouble(values.get("FastK")),
              Double.parseDouble(values.get("FastD"))
      )));
      return new STOCHF(metaData, indicators);
    }
  }
}


