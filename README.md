[![Build Status](https://travis-ci.org/patriques82/alphavantage4j.svg?branch=master)](https://travis-ci.org/patriques82/alphavantage4j)
[![codecov](https://codecov.io/gh/patriques82/alphavantage4j/branch/master/graph/badge.svg)](https://codecov.io/gh/patriques82/alphavantage4j)
[![license](https://img.shields.io/github/license/patriques82/alphavantage4j.svg)](https://github.com/patriques82/alphavantage4j/blob/master/LICENSE)

# alphavantage4j

A Java wrapper to get stock data and stock indicators from the Alpha Vantage API.

## Introduction

Alpha Vantage delivers a free API for real time financial data and most used finance indicators. This library implements a wrapper to the free API provided by Alpha
Vantage (http://www.alphavantage.co/). It requires an api key, that can be requested on http://www.alphavantage.co/support/#api-key. You can have a look at all the api 
calls available in their documentation http://www.alphavantage.co/documentation.

## Gradle installation

```groovy
repositories {
    maven {
        url  "https://dl.bintray.com/patriques82/maven" 
    }
}
dependencies {
    compile 'org.patriques:alphavantage4j:1.4'
}
```

## Usage

Now that you set up your project and have your api key you can start using the service. Here are a few examples of how you can use the service.

#### Time Series example
```java
public class App {
  public static void main(String[] args) {
    String apiKey = "50M3AP1K3Y";
    int timeout = 3000;
    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
    TimeSeries stockTimeSeries = new TimeSeries(apiConnector);
    
    try {
      IntraDay response = stockTimeSeries.intraDay("MSFT", Interval.ONE_MIN, OutputSize.COMPACT);
      Map<String, String> metaData = response.getMetaData();
      System.out.println("Information: " + metaData.get("1. Information"));
      System.out.println("Stock: " + metaData.get("2. Symbol"));
      
      List<StockData> stockData = response.getStockData();
      stockData.forEach(stock -> {
        System.out.println("date:   " + stock.getDateTime());
        System.out.println("open:   " + stock.getOpen());
        System.out.println("high:   " + stock.getHigh());
        System.out.println("low:    " + stock.getLow());
        System.out.println("close:  " + stock.getClose());
        System.out.println("volume: " + stock.getVolume());
      });
    } catch (AlphaVantageException e) {
      System.out.println("something went wrong");
    }
  }
}
```
#### Foreign Exchange example
```java
public class App {
  public static void main(String[] args) {
    String apiKey = "50M3AP1K3Y";
    int timeout = 3000;
    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
    ForeignExchange foreignExchange = new ForeignExchange(apiConnector);

    try {
      CurrencyExchange currencyExchange = foreignExchange.currencyExchangeRate("USD", "SEK");
      CurrencyExchangeData currencyExchangeData = currencyExchange.getData();

      System.out.println("from currency code: " + currencyExchangeData.getFromCurrencyCode());
      System.out.println("from currency name: " + currencyExchangeData.getFromCurrencyName());
      System.out.println("to currency code:   " + currencyExchangeData.getToCurrencyCode());
      System.out.println("to currency name:   " + currencyExchangeData.getToCurrencyName());
      System.out.println("exchange rate:      " + currencyExchangeData.getExchangeRate());
      System.out.println("last refresh:       " + currencyExchangeData.getTime());
    } catch (AlphaVantageException e) {
      System.out.println("something went wrong");
    }
  }
}
```
#### Digital/Crypto Currencies example
```java
public class App {
  public static void main(String[] args) {
    String apiKey = "50M3AP1K3Y";
    int timeout = 3000;
    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
    DigitalCurrencies digitalCurrencies = new DigitalCurrencies(apiConnector);

    try {
      IntraDay response = digitalCurrencies.intraDay("BTC", Market.USD);
      Map<String, String> metaData = response.getMetaData();
      System.out.println("Information: " + metaData.get("1. Information"));
      System.out.println("Digital Currency Code: " + metaData.get("2. Digital Currency Code"));

      List<SimpelDigitalCurrencyData> digitalData = response.getDigitalData();
      digitalData.forEach(data -> {
        System.out.println("date:       " + data.getDateTime());
        System.out.println("price A:    " + data.getPriceA());
        System.out.println("price B:    " + data.getPriceB());
        System.out.println("volume:     " + data.getVolume());
        System.out.println("market cap: " + data.getMarketCap());
      });
    } catch (AlphaVantageException e) {
      System.out.println("something went wrong");
    }
  }
}
```
#### Technical Indicators example
```java
public class App {
  public static void main(String[] args) {
    String apiKey = "50M3AP1K3Y";
    int timeout = 3000;
    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
    TechnicalIndicators technicalIndicators = new TechnicalIndicators(apiConnector);

    try {
      MACD response = technicalIndicators.macd("MSFT", Interval.DAILY, TimePeriod.of(10), SeriesType.CLOSE, null, null, null);
      Map<String, String> metaData = response.getMetaData();
      System.out.println("Symbol: " + metaData.get("1: Symbol"));
      System.out.println("Indicator: " + metaData.get("2: Indicator"));

      List<MACDData> macdData = response.getData();
      macdData.forEach(data -> {
        System.out.println("date:           " + data.getDateTime());
        System.out.println("MACD Histogram: " + data.getHist());
        System.out.println("MACD Signal:    " + data.getSignal());
        System.out.println("MACD:           " + data.getMacd());
      });
    } catch (AlphaVantageException e) {
      System.out.println("something went wrong");
    }
  }
}
```
#### Sector Performances example
```java
public class App {
  public static void main(String[] args) {
    String apiKey = "50M3AP1K3Y";
    int timeout = 3000;
    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
    SectorPerformances sectorPerformances = new SectorPerformances(apiConnector);

    try {
      Sectors response = sectorPerformances.sector();
      Map<String, String> metaData = response.getMetaData();
      System.out.println("Information: " + metaData.get("Information"));
      System.out.println("Last Refreshed: " + metaData.get("Last Refreshed"));

      List<SectorData> sectors = response.getSectors();
      sectors.forEach(data -> {
        System.out.println("key:           " + data.getKey());
        System.out.println("Consumer Discretionary: " + data.getConsumerDiscretionary());
        System.out.println("Consumer Staples:       " + data.getConsumerStaples());
        System.out.println("Energy:                 " + data.getEnergy());
        System.out.println("Financials:             " + data.getFinancials());
        System.out.println("Health Care:            " + data.getHealthCare());
        System.out.println("Industrials:            " + data.getIndustrials());
        System.out.println("Information Technology: " + data.getInformationTechnology());
        System.out.println("Materials:              " + data.getMaterials());
        System.out.println("Real Estate:            " + data.getRealEstate());
      });
    } catch (AlphaVantageException e) {
      System.out.println("something went wrong");
    }
  }
}
```