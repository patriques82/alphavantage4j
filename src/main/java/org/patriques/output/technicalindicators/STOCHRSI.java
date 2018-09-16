package org.patriques.output.technicalindicators;

import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.JsonParser;
import org.patriques.output.technicalindicators.data.STOCHDataFast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the stochastic relative strength index (STOCHRSI) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class STOCHRSI extends TechnicalIndicatorResponse<STOCHDataFast> {

  private STOCHRSI(final Map<String, String> metaData,
                   final List<STOCHDataFast> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code STOCHRSI} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return STOCHRSI instance
   */
  public static STOCHRSI from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code STOCHRSI}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<STOCHRSI> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: STOCHRSI";
    }

    @Override
    STOCHRSI resolve(Map<String, String> metaData, Map<String, Map<String, String>> indicatorData) {
      List<STOCHDataFast> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new STOCHDataFast(
              resolveDate(key),
              Double.parseDouble(values.get("FastK")),
              Double.parseDouble(values.get("FastD"))
      )));
      return new STOCHRSI(metaData, indicators);
    }
  }
}


