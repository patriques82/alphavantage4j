package org.patriques.input;

public class Keywords implements ApiParameter {

    private String keywords;

    public Keywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String getKey() {
        return "keywords";
    }

    @Override
    public String getValue() {
        return keywords;
    }
}
