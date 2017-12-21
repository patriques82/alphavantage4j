package org.patriques.input;

import org.junit.Test;
import org.patriques.input.digitalcurrencies.Market;
import org.patriques.input.exchange.FromCurrency;
import org.patriques.input.exchange.ToCurrency;
import org.patriques.input.technicalindicators.Interval;
import org.patriques.input.technicalindicators.SeriesType;
import org.patriques.input.technicalindicators.TimePeriod;
import org.patriques.input.timeseries.OutputSize;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApiParameterBuilderTest {

  @Test
  public void digitalCurrencies() {
    ApiParameterBuilder apiParameterBuilder = new ApiParameterBuilder();
    apiParameterBuilder.append(Function.DIGITAL_CURRENCY_DAILY);
    apiParameterBuilder.append(Market.ZWL);

    String url = apiParameterBuilder.getUrl();

    assertThat(url, equalTo("&function=DIGITAL_CURRENCY_DAILY&market=ZWL"));
  }

  @Test
  public void exchange() {
    ApiParameterBuilder apiParameterBuilder = new ApiParameterBuilder();
    apiParameterBuilder.append(new FromCurrency("BTC"));
    apiParameterBuilder.append(new ToCurrency("USD"));

    String url = apiParameterBuilder.getUrl();

    assertThat(url, equalTo("&from_currency=BTC&to_currency=USD"));
  }

  @Test
  public void technicalIndicators() {
    ApiParameterBuilder apiParameterBuilder = new ApiParameterBuilder();
    apiParameterBuilder.append(Interval.FIFTEEN_MIN);
    apiParameterBuilder.append(SeriesType.HIGH);
    apiParameterBuilder.append(TimePeriod.of(2));

    String url = apiParameterBuilder.getUrl();

    assertThat(url, equalTo("&interval=15min&series_type=high&time_period=2"));
  }

  @Test
  public void timeSeries() {
    ApiParameterBuilder apiParameterBuilder = new ApiParameterBuilder();
    apiParameterBuilder.append(new Symbol("DUMMY"));
    apiParameterBuilder.append(Function.TIME_SERIES_DAILY);
    apiParameterBuilder.append(Interval.FIFTEEN_MIN);
    apiParameterBuilder.append(OutputSize.FULL);

    String url = apiParameterBuilder.getUrl();

    assertThat(url, equalTo("&symbol=DUMMY&function=TIME_SERIES_DAILY&interval=15min&outputsize=full"));
  }
}
