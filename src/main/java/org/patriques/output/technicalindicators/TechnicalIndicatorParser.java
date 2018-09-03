package org.patriques.output.technicalindicators;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.JsonParser;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Since the format for the technical indicator responses differ slightly but on the whole
 * have the same structure the {@code TechnicalIndicatorParser} extracts the similarity of
 * the parsing to this class.
 *
 * @see JsonParser
 * @param <Data> the response for each individual Response, i.e MACD, EMA etc.
 */
public abstract class TechnicalIndicatorParser<Data> extends JsonParser<Data> {

  private final Interval interval;

  public TechnicalIndicatorParser(Interval interval) {
    this.interval = interval;
  }

  /**
   * The specifics of the resolution is pushed down to each response type, i.e MACD, EMA etc.
   *
   * @param metaData the meta data
   * @param indicatorData the indicator data
   * @return the response for each individual response, i.e MACD, EMA etc.
   */
  abstract Data resolve(Map<String, String> metaData,
                        Map<String, Map<String, String>> indicatorData);

  /**
   * Gets the key for the indicators, this differs for each response type, i.e MACD, EMA etc.
   * This is used by the resolve method below.
   *
   * @return the indicator data key
   */
  abstract String getIndicatorKey();

  /**
   * Helper method for resolving local date time. If the key to the data object doesn´t specify hours and minutes it
   * returns the hour at beginning of that day.
   *
   * @param key the key to the data object, i.e: "2017-12-01 11:15" or "2017-12-01" depending on interval
   * @return the {@link LocalDateTime} instance
   */
  protected LocalDateTime resolveDate(String key) {
    switch (interval) {
      case MONTHLY:
      case WEEKLY:
      case DAILY:
    		if(key.matches(DATE_WITH_TIME_PATTERN)) {
    			return LocalDate.parse(key, DATE_WITH_TIME_FORMAT).atStartOfDay();
    		}
    		else if (key.matches(DATE_WITH_SIMPLE_TIME_PATTERN)) {
    			return LocalDate.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT).atStartOfDay();
    		}
    		else if (key.matches(SIMPLE_DATE_PATTERN)) {
    			return LocalDate.parse(key, SIMPLE_DATE_FORMAT).atStartOfDay();
    		}
      default:
        return LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT);
    }
  }

  @Override
  protected Data resolve(JsonObject rootObject) {
    Type metaDataType = new TypeToken<Map<String, String>>() {
    }.getType();
    Type dataType = new TypeToken<Map<String, Map<String, String>>>() {
    }.getType();
    try {
      Map<String, String> metaData = GSON.fromJson(rootObject.get("Meta Data"), metaDataType);
      Map<String, Map<String, String>> indicatorData = GSON.fromJson(rootObject.get(getIndicatorKey()), dataType);
      return resolve(metaData, indicatorData);
    } catch (JsonSyntaxException e) {
      throw new AlphaVantageException("technical indicators api change", e);
    }
  }
}
