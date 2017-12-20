[![Build Status](https://travis-ci.org/patriques82/alphavantage4j.svg?branch=master)](https://travis-ci.org/patriques82/alphavantage4j)
[![license](https://img.shields.io/github/license/patriques82/alphavantage4j.svg)](https://github.com/patriques82/alphavantage4j/blob/master/LICENSE)

# alphavantage4j

A Java wrapper to get stock data and stock indicators from the Alpha Vantage API*
(This is still a work in progress)

## Introduction

Alpha Vantage delivers a free API for real time financial data and most used finance indicators. This library implements a wrapper to the free API provided by Alpha
Vantage (http://www.alphavantage.co/). It requires an api key, that can be requested on http://www.alphavantage.co/support/#api-key. You can have a look at all the api 
calls available in their documentation http://www.alphavantage.co/documentation

## Maven installation

#### Settings

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<settings xsi:schemaLocation='http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd'
          xmlns='http://maven.apache.org/SETTINGS/1.0.0' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>
    <profiles>
        <profile>
            <repositories>
                <repository>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                    <id>bintray-patriques82-maven</id>
                    <name>bintray</name>
                    <url>https://dl.bintray.com/patriques82/maven</url>
                </repository>
            </repositories>
            <pluginRepositories>
                <pluginRepository>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                    <id>bintray-patriques82-maven</id>
                    <name>bintray-plugins</name>
                    <url>https://dl.bintray.com/patriques82/maven</url>
                </pluginRepository>
            </pluginRepositories>
            <id>bintray</id>
        </profile>
    </profiles>
    <activeProfiles>
        <activeProfile>bintray</activeProfile>
    </activeProfiles>
</settings>
```
#### Dependency

```xml
<dependency>
  <groupId>org.patriques</groupId>
  <artifactId>alphavantage4j</artifactId>
  <version>1.0</version>
  <type>pom</type>
</dependency>
```

## Gradle installation

```groovy
repositories {
    maven {
        url  "https://dl.bintray.com/patriques82/maven" 
    }
}
dependencies {
    compile 'org.patriques:alphavantage4j:1.0'
}
```



## Usage
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
        System.out.println("*****************************");
        System.out.println("date:   " + stock.getDateTime());
        System.out.println("open:   " + stock.getOpen());
        System.out.println("high:   " + stock.getHigh());
        System.out.println("low:    " + stock.getLow());
        System.out.println("close:  " + stock.getClose());
        System.out.println("volume: " + stock.getVolume());
        System.out.println("*****************************");
      });
    } catch (AlphaVantageException e) {
      System.out.println("something went wrong");
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
    
### Standards
    
  * Use 2 spaces for indentation.
  * Make sure to document the code you write.