package org.patriques.output.search;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.JsonParser;
import org.patriques.output.quote.BatchStockQuotesResponse;
import org.patriques.output.search.data.SearchResult;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of search endpoint response from api.
 *
 * @see TimeSeries
 */
public class SearchResultsResponse {

    private final List<SearchResult> bestMatches;

    public SearchResultsResponse(List<SearchResult> bestMatches) {
        this.bestMatches = bestMatches;
    }

    /**
     * List of SearchResult
     *
     * @return list of {@link SearchResult}
     */
    public List<SearchResult> getBestMatches() {
        return this.bestMatches;
    }

    /**
     * Creates {@code SearchResultsResponse} instance from json.
     *
     * @param json string to parse
     * @return SearchResultsResponse instance
     */
    public static SearchResultsResponse from(String json) {
        Parser parser = new Parser();
        return parser.parseJson(json);
    }

    /**
     * Helper class for parsing json to {@code BatchStockQuotesResponse}.
     *
     * @see JsonParser
     */
    private static class Parser extends JsonParser<SearchResultsResponse> {

        @Override
        protected SearchResultsResponse resolve(final JsonObject rootObject) {
            Type dataType = new TypeToken<List<Map<String, String>>>() {
            }.getType();
            try {
                List<Map<String, String>> stockQuotes = GSON.fromJson(rootObject.get("bestMatches"), dataType);
                List<SearchResult> searchResults = new ArrayList<>();
                stockQuotes.forEach((searchData) -> searchResults.add(new SearchResult(
                        searchData.get("1. symbol"),
                        searchData.get("2. name"),
                        searchData.get("3. type"),
                        searchData.get("4. region"),
                        LocalTime.parse(searchData.get("5. marketOpen")),
                        LocalTime.parse(searchData.get("6. marketClose")),
                        searchData.get("7. timezone"),
                        searchData.get("8. currency"),
                        Float.parseFloat(searchData.get("9. matchScore"))
                )));

                return new SearchResultsResponse(searchResults);
            } catch (JsonSyntaxException e) {
                throw new AlphaVantageException("BatchStockQuotes api change", e);
            }
        }
    }
}
