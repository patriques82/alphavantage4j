package org.patriques.output.exchange;

import java.util.List;
import java.util.Map;

/**
 * @author ilker Kopan
 */
public class ForexResponse<Data> {
    private final Map<String, String> metaData;
    private final List<Data> forexData;

    public ForexResponse(Map<String, String> metaData,
                                   List<Data> forexData) {
        this.metaData = metaData;
        this.forexData = forexData;
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }

    public List<Data> getForexData() {
        return forexData;
    }
}
