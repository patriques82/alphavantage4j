package org.patriques;

import static org.junit.Assert.assertEquals;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

import org.junit.Test;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.exchange.Daily;

/**
 * @author Kilian
 *
 */
public class AlphaVantageConnectorTest {
	
	@Test
	public void testProxy() {
		String apiKey = "50M3AP1K3Y";
		int timeout = 3000;
		AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
		
		Proxy proxy = new Proxy(Type.HTTP,new InetSocketAddress("178.128.153.253",3128));
		AlphaVantageConnector proxyapiConnector = new AlphaVantageConnector(apiKey, timeout,proxy);
		
		Daily x = new ForeignExchange(apiConnector).daily("EUR","USD",OutputSize.COMPACT);
		Daily y =  new ForeignExchange(proxyapiConnector).daily("EUR","USD",OutputSize.COMPACT);
		assertEquals(x.getMetaData(),y.getMetaData());
	}

}
