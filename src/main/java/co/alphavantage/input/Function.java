package co.alphavantage.input;

/**
 * The function parameter for the api calls.
 */
public enum Function implements ApiParameter {

  // Time Series functions
  TIME_SERIES_INTRADAY("TIME_SERIES_INTRADAY"),
  TIME_SERIES_DAILY("TIME_SERIES_DAILY"),
  TIME_SERIES_DAILY_ADJUSTED("TIME_SERIES_DAILY_ADJUSTED"),
  TIME_SERIES_WEEKLY("TIME_SERIES_WEEKLY"),
  TIME_SERIES_WEEKLY_ADJUSTED("TIME_SERIES_WEEKLY_ADJUSTED"),
  TIME_SERIES_MONTHLY("TIME_SERIES_MONTHLY"),
  TIME_SERIES_MONTHLY_ADJUSTED("TIME_SERIES_MONTHLY_ADJUSTED"),

  // Foreign Exchange function
  CURRENCY_EXCHANGE_RATE("CURRENCY_EXCHANGE_RATE"),

  // Digital Currencies functions
  DIGITAL_CURRENCY_INTRADAY("DIGITAL_CURRENCY_INTRADAY"),
  DIGITAL_CURRENCY_DAILY("DIGITAL_CURRENCY_DAILY"),
  DIGITAL_CURRENCY_WEEKLY("DIGITAL_CURRENCY_WEEKLY"),
  DIGITAL_CURRENCY_MONTHLY("DIGITAL_CURRENCY_MONTHLY"),

  // Sector Performance functions
  SECTOR("SECTOR"),

  // Technical Indicator functions
  SMA("SMA"),
  EMA("EMA"),
  MACD("MACD"),
  DEMA("DEMA"),
  WMA("WMA"),
  TEMA("TEMA");

  private final String urlParameter;

  Function(String urlParameter) {
    this.urlParameter = urlParameter;
  }

  @Override
  public String getKey() {
    return "function";
  }

  @Override
  public String getValue() {
    return urlParameter;
  }
}
