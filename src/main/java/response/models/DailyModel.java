package response.models;

import org.joda.time.DateTime;
import response.data.MetaData;
import response.data.StockData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DailyModel extends ResponseModel {

  @Override
  public MetaData resolveMetaData(Map<String, String> metaDataResponse) {
    return new MetaData(
            metaDataResponse.get("1. Information"),
            metaDataResponse.get("2. Symbol"),
            metaDataResponse.get("3. Last Refreshed"),
            null,
            metaDataResponse.get("4. Output Size"),
            metaDataResponse.get("5. Time Zone")
    );
  }

  @Override
  public String getStocksKey() {
    return "Time Series (Daily)";
  }

  @Override
  public List<StockData> resolveStockData(Map<String, Map<String, String>> stockDataResponse) {
    List<StockData> stockData = new ArrayList<>();
    stockDataResponse.forEach((k, v) -> {
      stockData.add(new StockData(
              DateTime.parse(k, ResponseModel.DATE_FORMAT),
              Double.parseDouble(v.get("1. open")),
              Double.parseDouble(v.get("2. high")),
              Double.parseDouble(v.get("3. low")),
              Double.parseDouble(v.get("4. close")),
              Long.parseLong(v.get("5. volume"))
      ));
    });
    return stockData;
  }
}
