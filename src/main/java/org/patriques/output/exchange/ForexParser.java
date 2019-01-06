package org.patriques.output.exchange;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.JsonParser;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author ilker Kopan
 *
 * Intraday, Daily, Weekly And Monthly data response is different from Exchange Rates.
 *
 * @see JsonParser
 * @param <Data> the response for each individual Response, i.e Intraday, Daily, Weekly etc.
 */
abstract class ForexParser<Data> extends JsonParser<Data> {
    /**
     * The specifics of the resolution is pushed down to each response type, i.e Intraday, Daily etc.
     *
     * @param metaData the meta data
     * @param fxData the digital currency data
     * @return the response for each individual response, i.e Intraday, Daily etc.
     */
    abstract Data resolve(Map<String, String> metaData,
                          Map<String, Map<String, String>> fxData) ;

    /**
     * Gets the key for the fx data, this differs for each response type, i.e Intraday, Daily etc.
     * This is used by the resolve method below.
     *
     * @return the fx data key
     */
    abstract String getForexDataKey();

    @Override
    public Data resolve(JsonObject rootObject)  {
        Type metaDataType = new TypeToken<Map<String, String>>() {
        }.getType();
        Type dataType = new TypeToken<Map<String, Map<String, String>>>() {
        }.getType();
        try {
            Map<String, String> metaData = GSON.fromJson(rootObject.get("Meta Data"), metaDataType);
            Map<String, Map<String, String>> fxData = GSON.fromJson(rootObject.get(getForexDataKey()), dataType);
            return resolve(metaData, fxData);
        } catch (JsonSyntaxException e) {
            throw new AlphaVantageException("FX api change", e);
        }
    }
}
