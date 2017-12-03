package parameters;

/**
 * from_currency parameter, i.e from_currency=EUR
 */
public enum FromCurrency implements ApiParameter {

	EUR("EUR");
	
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
