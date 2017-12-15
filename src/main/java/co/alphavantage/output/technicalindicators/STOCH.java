package co.alphavantage.output.technicalindicators;

import co.alphavantage.input.technicalindicators.Interval;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.SlowSTOCHData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the stochastic oscillator (STOCH) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class STOCH extends TechnicalIndicatorResponse<SlowSTOCHData> {

  private STOCH(final Map<String, String> metaData,
                final List<SlowSTOCHData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code STOCH} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return STOCH instance
   */
  public static STOCH from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code STOCH}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<STOCH> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: STOCH";
    }

    @Override
    STOCH resolve(Map<String, String> metaData, Map<String, Map<String, String>> indicatorData) {
      List<SlowSTOCHData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new SlowSTOCHData(
              resolveDate(key),
              Double.parseDouble(values.get("SlowD")),
              Double.parseDouble(values.get("SlowK"))
      )));
      return new STOCH(metaData, indicators);
    }
  }
}

