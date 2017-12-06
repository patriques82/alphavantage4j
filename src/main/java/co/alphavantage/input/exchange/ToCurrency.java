package co.alphavantage.input.exchange;

import co.alphavantage.input.ApiParameter;

public class ToCurrency implements ApiParameter {
	
	private final String urlParameter;
	
	ToCurrency(String urlParameter) {
	    this.urlParameter = urlParameter;
	}

	@Override
	public String getKey() {
		return "to_currency";	
	}

	@Override
	public String getValue() {
		return urlParameter;	
	}

}
