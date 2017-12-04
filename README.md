[![Build Status](https://travis-ci.org/patriques82/alphavantage4j.svg?branch=master)](https://travis-ci.org/patriques82/alphavantage4j)

# alphavantage4j

A Java wrapper to get stock data and stock indicators from the Alpha Vantage API*

## Introduction

Alpha Vantage delivers a free API for real time financial data and most used finance indicators. This library implements a wrapper to the free API provided by Alpha
Vantage (http://www.alphavantage.co/). It requires an api key, that can be requested on http://www.alphavantage.co/support/#api-key. You can have a look at all the api 
calls available in their documentation http://www.alphavantage.co/documentation

## Installation

```sh
git clone https://github.com/patriques82/alphavantage4j.git
cd alphavantage4j
./gradlew publishToMavenLocal
```
Now you have the artifact in your local maven repository and can be included in your build file

### Including in Gradle project

```groovy
repositories {
  mavenLocal()
}

dependencies {
  compile group: 'co.alphavantage', name: 'alphavantage4j', version: '1.0-SNAPSHOT'
}
```

### Including in Maven project
```xml
<dependency>
    <groupId>co.alphavantage</groupId>
    <artifactId>alphavantage4j</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## Usage
```java
public class App {
  public static void main(String[] args) {
    String apiKey = "50M3AP1K3Y";
    int timeout = 3000;
    co.alphavantage4j.AlphaVantageConnector apiConnector = new co.alphavantage4j.AlphaVantageConnector(apiKey, timeout);
    co.alphavantage4j.TimeSeries stockTimeSeries = new co.alphavantage4j.TimeSeries(apiConnector);
    
    Either<IntraDay, Exception> response = stockTimeSeries.intraDay("MSFT", Interval.ONE_MIN, OutputSize.COMPACT);
    if (response.isLeft()) {
      MetaData metaData = response.getLeft().getMetaData();
      System.out.println("Stock: " + metaData.getSymbol());
      
      List<StockData> stockData = response.getLeft().getStockData();
      stockData.forEach(stock -> {
        System.out.println("*****************************");
        System.out.println("date:   " + stock.getDateTime());
        System.out.println("open:   " + stock.getOpen());
        System.out.println("high:   " + stock.getHigh());
        System.out.println("low:    " + stock.getLow());
        System.out.println("close:  " + stock.getClose());
        System.out.println("volume: " + stock.getVolume());
        System.out.println("*****************************");
      });
    } else {
      response.getRight().printStackTrace();
    }
  }
}
```

## Contributing
   
  * Fork the project.
  * Make your feature addition or bug fix.
  * Add tests for it.
  * Commit, do not mess with buildfile, version, or history.
  * Send a pull request. Bonus points for topic branches.

If you want to work on the code in an IDE instead of a text editor you can
easily create project files with gradle:

    $ gradle idea     # Intellij IDEA
    $ gradle eclipse  # Eclipse