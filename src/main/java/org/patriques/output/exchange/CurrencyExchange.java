package org.patriques.output.exchange;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.JsonParser;
import org.patriques.output.exchange.data.CurrencyExchangeData;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

/**
 * Representation of currency exchange.
 */
public class CurrencyExchange {

  private CurrencyExchangeData data;

  CurrencyExchange(final CurrencyExchangeData data) {
    this.data = data;
  }

  public CurrencyExchangeData getData() {
    return data;
  }

  /**
   * Create Currency Exchange data representation from json object
   *
   * @param json string to parse
   * @return Currency Exchange data
   */
  public static CurrencyExchange from(String json) {
    CurrencyExchangeParser parser = new CurrencyExchangeParser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code Currency Exchange}
   */
  private static class CurrencyExchangeParser extends JsonParser<CurrencyExchange> {
    public CurrencyExchange resolve(JsonObject rootObject) {
      Type dataType = new TypeToken<Map<String, Map<String, String>>>() {
      }.getType();
      try {
        Map<String, Map<String, String>> data = GSON.fromJson(rootObject, dataType);
        CurrencyExchangeData exchangeData = createCurrencyExchangeData(data.values().stream()
                .findFirst()
                .orElse(Collections.emptyMap()));
        return new CurrencyExchange(exchangeData);
      } catch (JsonSyntaxException e) {
        throw new AlphaVantageException("currency exchange api change", e);
      }
    }

    private CurrencyExchangeData createCurrencyExchangeData(Map<String, String> values) throws JsonSyntaxException {
      return new CurrencyExchangeData(
              values.get("1. From_Currency Code"),
              values.get("2. From_Currency Name"),
              values.get("3. To_Currency Code"),
              values.get("4. To_Currency Name"),
              Float.parseFloat(values.get("5. Exchange Rate")),
              LocalDateTime.parse(values.get("6. Last Refreshed"), DATE_WITH_TIME_FORMAT),
              values.get("7. Time Zone")
      );
    }

  }


}
