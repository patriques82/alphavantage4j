package org.patriques.output.quote.data;

import java.time.LocalDateTime;

/**
 * Representation of json object, i.e:
 * {
 *   "1. symbol": "MSFT",
 *   "2. price": "96.3850",
 *   "3. volume": "--",
 *   "4. timestamp": "2018-05-18 15:59:48"
 * }
 */
public class StockQuote {
  private final String symbol;
  private final double price;
  private final long volume;
  private final LocalDateTime timestamp;

  public StockQuote(final String symbol, final double price, final long volume, final LocalDateTime timestamp) {
    this.symbol = symbol;
    this.price = price;
    this.volume = volume;
    this.timestamp = timestamp;
  }

  public String getSymbol() {
    return symbol;
  }

  public double getPrice() {
    return price;
  }

  public long getVolume() {
    return volume;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }
}
