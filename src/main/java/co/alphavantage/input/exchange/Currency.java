package co.alphavantage.input.exchange;

import co.alphavantage.input.ApiParameter;

/**
* Interval parameter, i.e interval=15min
*/
public class Currency {
	
  private static final String TO_CURRENCY = "to_currency";
  private static final String FROM_CURRENCY = "from_currency";

  private From fromCurrency;
  private To toCurrency;

  public Currency(String urlParameter) {
    this.toCurrency = new To(urlParameter);
    this.fromCurrency = new From(urlParameter);
  }

  public From from() {
    return fromCurrency;
  }

  public To to() {
    return toCurrency;
  }

  public class To extends FromTo {
    To(String urlParameter) { super(urlParameter); }

    @Override
    public String getKey() {
      return TO_CURRENCY;
    }
  }

  public class From extends FromTo {
    From(String urlParameter) { 
    	super(urlParameter); 
    	}

    @Override
    public String getKey() {
      return FROM_CURRENCY;
    }
  }

  private abstract class FromTo implements ApiParameter {
    private String urlParameter;

    FromTo(String urlParameter) { 
    	this.urlParameter = urlParameter; 
    }

    @Override
    public String getValue() {
      return urlParameter;
    }
  }

}