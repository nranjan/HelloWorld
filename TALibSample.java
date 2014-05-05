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
	    // such as moving averages, moving average convergence / divergence
	    // relative strength index, etc

	    initTALib("ma", "sma3", "3","Sma", symbol, "close" );
	    initTALib("ma", "ema5", "5","Ema", symbol, "close" );
	    initTALib("macd","macd1", "12", "26","9", symbol, "close" );
	    initTALib("rsi","rsi1", "12", symbol, "close" );
	    initTALib("adx","adx1", "5", symbol );//initialize adx
	    initTALib("atr","atr1", "5", symbol );//initialize atr
	    initTALib("cci","cci1", "5", symbol );//initialize cci
	    initTALib("sar","sar1", "0.02", "0.2", symbol );//initialize parabolic sar

	    context.setDataFrequency(2, Context.Frequency.DAY);
	    context.setSymbols(symbol);
	    context.setPortfolioValue(BigDecimal.valueOf(100000));
	    context.setDataType(Event.Type.BAR);
	    context.setStartDate("03-9-2012");
	    context.setEndDate("31-10-2013");
	}

	/*
	*  onEvent is the callback when a market event happens. 
	*  The behaviour of how this is called depends on the context 
	*  object you initialized in initialize(Context context) 
	*/

	public void onEvent(Object object)
	{
	    	double sma3 = getData("sma3");
		double ema5 = getData("ema5");
		double macd1 = getData("macd1" , "macd");
		double macd1Hist = getData("macd1" , "macdhist");
		double macd1Sig = getData("maccd1","macdsig");
		double rsi1 = getData("rsi1");
		double adx1 = getData("adx1");//read data of adx variable
		double atr1 = getData("atr1");//read data of  atr variable
		double cci1 = getData("cci1");//read data of cci variable
		double sar1 = getData("sar1");//read data of probabilstic SAR variables
		
		// See the output of this in the Logs tab

		log("sma3: " + sma3);
		log("ema5: " + ema5);
		log("macd1: " + macd1);
		log("macd1hist: " + macd1Hist);
		log("macd1sig: " + macd1Sig);
		log("rsi1: " + rsi1);
		log("adx1 = " +adx1);
		log("atr1 = " +atr1);
		log("cci1 = " +cci1);
		log("sar1 = " +sar1);


	}

}
