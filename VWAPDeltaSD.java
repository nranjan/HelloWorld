import java.math.BigDecimal;

import com.breezetrader.Context;
import com.breezetrader.Event;
import com.breezetrader.OrderType;
import com.breezetrader.Strategy;
import com.breezetrader.Bar;
/*
*  This is a sample strategy intended to get you started.
*  For more details on the APIs and usage please visit 
*  http://docs.breezetrader.com
*
*/
public class VWAPDeltaSD extends Strategy {


        String symbol = "RELIANCE";
        double vwap, stddev;
        /*

        *  initialize your context,
        *  technical indicators other variables

        */
        public void initialize(Context context)
        {
            initTALib("vwap", "vwap", symbol, "close" ); // VWAP
            context.registerCEPQuery("stddev", "select 'stddev', stddev(value) from vwap as value");
            
            context.setDataFrequency(1, Context.Frequency.DAY);
            context.setSymbols(symbol);

            context.setPortfolioValue(BigDecimal.valueOf(150000));
            context.setDataType(Event.Type.BAR);

            context.setStartDate("01-01-2011");
            context.setEndDate("31-12-2013");

        }


        /*
        *  onEvent is the callback when a market event happens.

        *  The behaviour of how this is called depends on the context
        *  object you intialized in intialize(Context context)

        */

        public void onEvent(Object object)

        {
            vwap = getData("vwap");
            stddev = getData("stddev");

            log("VWAP : " + vwap);
            log("STDDEV(vwap) : " + stddev);
            
            if(object instanceof Bar){
                Bar data = (Bar) object;
                
                log("open : " + data.open + " | high : " + data.high 
                    + " | low : " + data.low + " | close : " + data.close);
                    
                    
                //Open Position VWAP + SD
                if(data.close.doubleValue() > vwap + stddev){
                    log("openpositions: "+getPosition(symbol));

                    if(getPosition(symbol) > 0){
                        closeAllPositions(symbol);

                    }
                    log("ID: "+order(OrderType.Market,symbol, -150));
                }
           
            
                //Close Position VWAP - SD
                 if(data.close.doubleValue() > vwap - stddev){
                    log("openpositions: "+getPosition(symbol));

                    if(getPosition(symbol) < 0){
                        closeAllPositions(symbol);

                    }
                    log("ID: "+order(OrderType.Market,symbol, 150));
                }
            }

            
            

        }


}
