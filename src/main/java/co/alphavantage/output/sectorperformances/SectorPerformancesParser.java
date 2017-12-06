package co.alphavantage.output.sectorperformances;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.sectorperformances.data.SectorData;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SectorPerformancesParser extends JsonParser<Sectors> {

  @Override
  public Sectors resolve(JsonObject rootObject) {
    Type dataType = new TypeToken<Map<String, Map<String, String>>>() {
    }.getType();
    try {
      Map<String, Map<String, String>> data = GSON.fromJson(rootObject, dataType);
      Map<String, String> metadata = data.remove("Meta Data");
      List<SectorData> sectors = new ArrayList<>();
      data.forEach((key, values) -> {
        try {
          sectors.add(createSectorData(key, values));
        } catch (ParseException e) {
          throw new AlphaVantageException("technical indicators api change", e);
        }
      });
      return new Sectors(metadata, sectors);
    } catch (JsonSyntaxException e) {
      throw new AlphaVantageException("technical indicators api change", e);
    }
  }

  private SectorData createSectorData(String key, Map<String, String> values) throws ParseException {
    return new SectorData(
            key,
            Double.parseDouble(values.getOrDefault("Information Technology", "0").trim().replace("%", "")),
            Double.parseDouble(values.getOrDefault("Health Care", "0").trim().replace("%", "")),
            Double.parseDouble(values.getOrDefault("Consumer Staples", "0").trim().replace("%", "")),
            Double.parseDouble(values.getOrDefault("Real Estate", "0").trim().replace("%", "")),
            Double.parseDouble(values.getOrDefault("Materials", "0").trim().replace("%", "")),
            Double.parseDouble(values.getOrDefault("Consumer Discretionary", "0").trim().replace("%", "")),
            Double.parseDouble(values.getOrDefault("Energy", "0").trim().replace("%", "")),
            Double.parseDouble(values.getOrDefault("Financials", "0").trim().replace("%", "")),
            Double.parseDouble(values.getOrDefault("Industrials", "0").trim().replace("%", "")),
            Double.parseDouble(values.getOrDefault("Utilities", "0").trim().replace("%", "")),
            Double.parseDouble(values.getOrDefault("Telecommunication Services", "0").trim().replace("%", ""))
    );
  }
}
