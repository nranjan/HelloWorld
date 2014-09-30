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
public class SmaCrossover extends Strategy {


        boolean flag = false;

        String symbol = "BANKNIFTY-CURRENT";
        double sma10, sma20, lsma10, lsma20;
        /*

        *  initialize your context,
        *  technical indicators other variables

        */
        public void initialize(Context context)
        {

            
            initTALib("ma", "sma10", "10","Sma", symbol, "close" );
            initTALib("ma", "sma20", "20","Sma", symbol, "close" );
            
            context.setDataFrequency(1, Context.Frequency.DAY);
            context.setSymbols(symbol);

            context.setPortfolioValue(BigDecimal.valueOf(150000));
            context.setDataType(Event.Type.BAR);

            context.setStartDate("01-09-2012");
            context.setEndDate("31-11-2012");

        }


        /*
        *  onEvent is the callback when a market event happens.

        *  The behaviour of how this is called depends on the context
        *  object you intialized in intialize(Context context)

        */

        public void onEvent(Object object)

        {
            lsma10 = sma10;
            lsma20 = sma20;

            sma10 = getData("sma10");
            sma20 = getData("sma20");


            //Open Position
            if(sma10>sma20 && lsma10 <= lsma20)

            {
                if(getPosition(symbol) == 0){
                    log("ordering");
                    order(OrderType.Market,symbol, 200);
                }
            }
            
            //Close Position

            if(object instanceof Bar){
                Bar data = (Bar) object;
                

                if(data.getCurrentValue() < sma20){
                    closeAllPositions(symbol);

                }
            }

        }


}
