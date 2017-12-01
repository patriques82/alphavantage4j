package output.time_series;

import java.util.List;
import java.util.Map;

public interface Response {

  Map<String, String> getMetaData();

  List<StockData> getStockData();
}
