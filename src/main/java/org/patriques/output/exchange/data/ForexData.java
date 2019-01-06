package org.patriques.output.exchange.data;

import java.time.LocalDateTime;

/**
 * Representation of json object, i.e:
 * "2018-12-25": {
 *             "1. open": "1.1413",
 *             "2. high": "1.1422",
 *             "3. low": "1.1364",
 *             "4. close": "1.1377"
 *         }
 *
 * @author ilker Kopan
 */
public class ForexData {
    private final LocalDateTime dateTime;
    private final double open;
    private final double high;
    private final double low;
    private final double close;

    public ForexData(LocalDateTime dateTime, double open, double high, double low, double close) {
        this.dateTime = dateTime;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }
}
