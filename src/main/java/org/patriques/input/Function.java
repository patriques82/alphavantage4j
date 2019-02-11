package org.patriques.input;

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
  TIME_SERIES_SYMBOL_SEARCH("SYMBOL_SEARCH"),

  // Stock Quotes
  BATCH_STOCK_QUOTES("BATCH_STOCK_QUOTES"),

  // Foreign Exchange function
  CURRENCY_EXCHANGE_RATE("CURRENCY_EXCHANGE_RATE"),
  FX_INTRADAY("FX_INTRADAY"),
  FX_DAILY("FX_DAILY"),

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
  WMA("WMA"),
  DEMA("DEMA"),
  TEMA("TEMA"),
  TRIMA("TRIMA"),
  KAMA("KAMA"),
  MAMA("MAMA"),
  T3("T3"),
  MACD("MACD"),
  MACDEXT("MACDEXT"),
  STOCH("STOCH"),
  STOCHF("STOCHF"),
  RSI("RSI"),
  STOCHRSI("STOCHRSI"),
  WILLR("WILLR"),
  ADX("ADX"),
  ADXR("ADXR"),
  APO("APO"),
  PPO("PPO"),
  MOM("MOM"),
  BOP("BOP"),
  CCI("CCI"),
  CMO("CMO"),
  ROC("ROC"),
  ROCR("ROCR"),
  AROON("AROON"),
  AROONOSC("AROONOSC"),
  MFI("MFI"),
  TRIX("TRIX"),
  ULTOSC("ULTOSC"),
  DX("DX"),
  MINUS_DI("MINUS_DI"),
  PLUS_DI("PLUS_DI"),
  MINUS_DM("MINUS_DM"),
  PLUS_DM("PLUS_DM"),
  BBANDS("BBANDS"),
  MIDPOINT("MIDPOINT"),
  MIDPRICE("MIDPRICE"),
  SAR("SAR"),
  TRANGE("TRANGE"),
  ATR("ATR"),
  NATR("NATR"),
  AD("AD"),
  ADOSC("ADOSC"),
  OBV("OBV"),
  HT_TRENDLINE("HT_TRENDLINE"),
  HT_SINE("HT_SINE"),
  HT_TRENDMODE("HT_TRENDMODE"),
  HT_DCPERIOD("HT_DCPERIOD"),
  HT_DCPHASE("HT_DCPHASE"),
  HT_PHASOR("HT_PHASOR");

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
