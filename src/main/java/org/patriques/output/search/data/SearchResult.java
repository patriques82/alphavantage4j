package org.patriques.output.search.data;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Representation of json object, i.e:
 * {
 *   "1. symbol": "BA",
 *   "2. name": "The Boeing Company",
 *   "3. type": "Equity",
 *   "4. region": "United States",
 *   "5. marketOpen": "09:30",
 *   "6. marketClose": "16:00",
 *   "7. timezone": "UTC-05",
 *   "8. currency": "USD",
 *   "9. matchScore": "1.0000"
 * }
 */
public class SearchResult implements Serializable {
    private static final long serialVersionUID = -7098758014818612620L;

    private final String symbol;
    private final String name;
    private final String type;
    private final String region;
    private final LocalTime marketOpen;
    private final LocalTime marketClose;
    private final String timezone;
    private final String currency;
    private final float matchScore;

    public SearchResult(String symbol, String name, String type, String region, LocalTime marketOpen, LocalTime marketClose, String timezone, String currency, float matchScore) {
        this.symbol = symbol;
        this.name = name;
        this.type = type;
        this.region = region;
        this.marketOpen = marketOpen;
        this.marketClose = marketClose;
        this.timezone = timezone;
        this.currency = currency;
        this.matchScore = matchScore;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getRegion() {
        return this.region;
    }

    public LocalTime getMarketOpen() {
        return this.marketOpen;
    }

    public LocalTime getMarketClose() {
        return this.marketClose;
    }

    public String getTimezone() {
        return this.timezone;
    }

    public String getCurrency() {
        return this.currency;
    }

    public float getMatchScore() {
        return this.matchScore;
    }
}
