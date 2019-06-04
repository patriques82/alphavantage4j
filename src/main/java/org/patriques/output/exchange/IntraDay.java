package org.patriques.output.exchange;

import org.patriques.input.timeseries.Interval;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.JsonParser;
import org.patriques.output.exchange.data.ForexData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of intraday response from api.
 *
 * @see ForexResponse
 */
public class IntraDay extends ForexResponse<ForexData> {
    private IntraDay(final Map<String, String> metaData,
                     final List<ForexData> forexData) {
        super(metaData, forexData);
    }

    /**
     * Creates {@code IntraDay} instance from json.
     *
     * @param json string to parse
     * @return IntraDay instance
     */
    public static IntraDay from(Interval interval, String json)  {
        Parser parser = new Parser(interval);
        return parser.parseJson(json);
    }

    /**
     * Helper class for parsing json to {@code IntraDay}.
     *
     * @see ForexParser
     * @see JsonParser
     */
    private static class Parser extends ForexParser<IntraDay> {
        private final Interval interval;

        Parser(Interval interval) {
            this.interval = interval;
        }

        @Override
        String getForexDataKey() {
            return "Time Series FX (" + this.interval.getValue() + ")";
        }

        @Override
        IntraDay resolve(Map<String, String> metaData, Map<String, Map<String, String>> fxData) {
            List<ForexData> prices = new ArrayList<>();
            try {
                fxData.forEach((key, values) -> prices.add(new ForexData(
                        LocalDateTime.parse(key, DATE_WITH_TIME_FORMAT),
                        Double.parseDouble(values.get("1. open")),
                        Double.parseDouble(values.get("2. high")),
                        Double.parseDouble(values.get("3. low")),
                        Double.parseDouble(values.get("4. close"))
                )));
            } catch (Exception e) {
                throw new AlphaVantageException("IntraDay api change", e);
            }

            return new IntraDay(metaData, prices);
        }
    }
}
