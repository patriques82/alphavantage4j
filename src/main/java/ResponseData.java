import java.util.List;

public class ResponseData {
  private final MetaData metaData;
  private final List<StockData> stockData;

  public ResponseData(MetaData metaData, List<StockData> stockData) {
    this.metaData = metaData;
    this.stockData = stockData;
  }

  public MetaData getMetaData() {
    return metaData;
  }
}
