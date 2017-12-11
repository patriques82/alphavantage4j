package co.alphavantage.output.exchange;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.exchange.data.CurrencyExchangeData;

public class CurrencyExchange {
	private CurrencyExchangeData data;
	
	CurrencyExchange(CurrencyExchangeData data){
		this.data = data;
	}
	

	public CurrencyExchangeData getData() {
		return data;
	}


  /**
   * Create Currency Exchange data representation from json object
   * @param json string to parse
   * @return Currency Exchange data
   * @throws AlphaVantageException
   */
	public static CurrencyExchange from(String json) throws AlphaVantageException {
		CurrencyExchangeParser parser = new CurrencyExchangeParser();
		return parser.parseJson(json);
	}

  /**
   * Helper class for parsing json to {@code Currency Exchange}
   */
	private static class CurrencyExchangeParser extends JsonParser<CurrencyExchange>{
		public CurrencyExchange resolve(JsonObject rootObject) {
			  Type dataType = new TypeToken<Map<String, Map<String, String>>>() {
		      }.getType();
		      try {
		        Map<String, Map<String,String>> data = GSON.fromJson(rootObject, dataType);
		        CurrencyExchangeData exchangeData = createCurrencyExchangeData(data.values().stream()
		        		.findFirst()
		        		.orElse(Collections.emptyMap()));
			    return new CurrencyExchange(exchangeData);
		      } catch (JsonSyntaxException e) {
		        throw new AlphaVantageException("technical indicators api change", e);
		      }
		}
		
		private CurrencyExchangeData createCurrencyExchangeData(Map<String, String> values) throws JsonSyntaxException {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			return new CurrencyExchangeData(
					  values.getOrDefault("1. From_Currency Code", "0").trim().replace("%", ""),
		              values.getOrDefault("2. From_Currency Name", "0").trim().replace("%", ""),
		              values.getOrDefault("3. To_Currency Code", "0").trim().replace("%", ""),
		              values.getOrDefault("4. To_Currency Name", "0").trim().replace("%", ""),
		              Float.parseFloat(values.getOrDefault("5. Exchange Rate", "0").trim().replace("%", "")),
		              LocalDateTime.parse(values.getOrDefault("6. Last Refreshed", "0").trim().replace("%", ""),formatter),
		              values.getOrDefault("7. Time Zone", "0").trim().replace("%", "")
					);
		}
		
	}
	

}
