package parameters;
/**
 * to_currency parameter, i.e from_currency=EUR
 */
public enum ToCurrency implements ApiParameter {

	USD("USD");
	
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
