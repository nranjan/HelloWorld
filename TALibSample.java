import java.math.BigDecimal;

import com.breezetrader.Context;
import com.breezetrader.Event;
import com.breezetrader.OrderType;
import com.breezetrader.Strategy;
import com.breezetrader.Tick;
/*
*  This is a sample strategy intended to get you started.
*  For more details on the APIs and usage please visit 
*  http://docs.breezetrader.com
*
*/
public class TALibSample extends Strategy {
	
	String symbol = "RELIANCE";
	/*
	*  initialize your context, 
	*  technical indicators other variables
	*/
	public void initialize(Context context)
	{	
	    // See below on sample of how to use technical analysis functions
	    // such as moving averages, 
	    
		initTALib("macd","macd1", "12", "26","9", symbol, "close" );
		initTALib("rsi","rsi1", "12", "EU0009652759", "close" );
		
		
		context.setDataFrequency(2, Context.Frequency.WEEK);
		context.setSymbols(symbol);
		context.setPortfolioValue(BigDecimal.valueOf(100000));
		context.setDataType(Event.Type.BAR);
		context.setStartDate("03-9-2012");
		context.setEndDate("31-10-2012");
	}
	
	/*
	*  onEvent is the callback when a market event happens. 
	*  The behaviour of how this is called depends on the context 
	*  object you intialized in intialize(Context context) 
	*/
	
	public void onEvent(Object object)
	{
		double macd1 = getData("macd1" , "macd");
		double macd1Hist = getData("macd1" , "macdhist");
		double macd1Sig = getData("maccd1","macdsig")
		double rsi1 = getData("rsi1");
		
		
		// See the output of this in the Logs tab
		
		log("macd1: "+macd1);
		log("macd1hist: "+macd1Hist);
		log("macd1sig: "+macd1Sig);
		
		
	}
	
}
	
