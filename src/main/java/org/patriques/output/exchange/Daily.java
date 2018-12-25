package org.patriques.output.exchange;

import org.patriques.output.AlphaVantageException;
import org.patriques.output.exchange.data.ForexData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of daily response from api.
 *
 * @see ForexResponse
 *
 * @author ilker Kopan
 */
public class Daily extends ForexResponse<ForexData> {

    private Daily(final Map<String, String> metaData,
                  final List<ForexData> digitalData) {
        super(metaData, digitalData);
    }

    /**
     * Creates {@code Daily} instance from json.
     *
     * @param json string to parse
     * @return Daily instance
     */
    public static Daily from(String json)  {
        Parser parser = new Parser();
        return parser.parseJson(json);
    }

    private static class Parser extends ForexParser<Daily> {
        @Override
        String getForexDataKey() {
            return "Time Series FX (Daily)";
        }

        @Override
        Daily resolve(Map<String, String> metaData,
                      Map<String, Map<String, String>> stockData)  {
            List<ForexData> prices = new ArrayList<>();
            try {
                stockData.forEach((key, values) -> prices.add(new ForexData(
                        LocalDate.parse(key, SIMPLE_DATE_FORMAT).atStartOfDay(),
                        Double.parseDouble(values.get("1. open")),
                        Double.parseDouble(values.get("2. high")),
                        Double.parseDouble(values.get("3. low")),
                        Double.parseDouble(values.get("4. close"))
                )));
            } catch (Exception e) {
                throw new AlphaVantageException("Daily api change", e);
            }
            return new Daily(metaData, prices);
        }
    }
}
