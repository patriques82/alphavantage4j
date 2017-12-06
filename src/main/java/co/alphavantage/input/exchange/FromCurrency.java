package co.alphavantage.input.exchange;

import co.alphavantage.input.ApiParameter;

public class FromCurrency implements ApiParameter {
		
	private final String urlParameter;
	
	FromCurrency(String urlParameter) {
	    this.urlParameter = urlParameter;
	}

	@Override
	public String getKey() {
		return "from_currency";	
	}

	@Override
	public String getValue() {
		return urlParameter;	
	}

}
