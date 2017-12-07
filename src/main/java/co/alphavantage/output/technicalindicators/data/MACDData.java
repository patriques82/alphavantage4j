package co.alphavantage.output.technicalindicators.data;

import org.joda.time.DateTime;

public class MACDData {
	private final DateTime dateTime;
	private final double macd;
	
	public MACDData(DateTime dateTime, double macd) {
		this.dateTime = dateTime;
		this.macd = macd;
	}
	
	public DateTime getDateTime() {
		return dateTime;
	}
	
	public double getMacd() {
		return macd;
	}
}
