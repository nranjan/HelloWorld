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
public class EmaCrossover extends Strategy {

        // This is for trending market
        boolean flag = false;

        String symbol = "INFY";
        double ma3, ma5, lma5, lma3;
        /*

        *  initialize your context,
        *  technical indicators other variables

        */
        public void initialize(Context context)
        {

            initTALib("ma", "ma3", "3","Ema", symbol, "close" );
            initTALib("ma", "ma5", "5","Ema", symbol, "close" );
            context.setDataFrequency(1, Context.Frequency.DAY);

            context.setSymbols(symbol);

            context.setPortfolioValue(BigDecimal.valueOf(150000));
            context.setDataType(Event.Type.BAR);

            context.setStartDate("01-01-2011");
            context.setEndDate("31-12-2013");

            log("Initialized");
            lma3 =0;

            lma5 =0;
            ma3 = 0;

            ma5 = 0;

        }


        /*
        *  onEvent is the callback when a market event happens.

        *  The behaviour of how this is called depends on the context
        *  object you intialized in intialize(Context context)

        */

        public void onEvent(Object object)

        {
            lma5= ma5;// talib_data("lma5");
            lma3= ma3;//talib_data("lma3");

            ma3 = getData("ma3");
            ma5 = getData("ma5");





            if(ma3>ma5 && lma3<= lma5)
            {
                log("openpositions: "+getPosition(symbol));

                if(getPosition(symbol) < 0){
                    closeAllPositions(symbol);

                }
                log("ID: "+order(OrderType.Stop,symbol, 150));


                    

            }
            if(ma3<ma5 && lma3>= lma5)
            {

                log("openpositions: "+getPosition(symbol));
                if(getPosition(symbol) > 0){

                    closeAllPositions(symbol);
                }

                //log("ID: "+order(OrderType.Market,symbol, -75));

            }


        }


}
