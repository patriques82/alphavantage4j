package org.patriques.input.symbol;

import org.patriques.input.ApiParameter;

/**
 * @author ilker Kopan
 */
public class FromSymbol implements ApiParameter {
    private static final long serialVersionUID = -3525549215269155612L;
    String fromSymbol;

    public FromSymbol(String fromSymbol) {
        this.fromSymbol = fromSymbol;
    }

    @Override
    public String getKey() {
        return "from_symbol";
    }

    @Override
    public String getValue() {
        return fromSymbol;
    }

}