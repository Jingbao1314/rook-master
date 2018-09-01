package control;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import pojo.Trade;
import util.JdbcMethod.TradeJdbc;

import java.util.List;

/**
 * Created by jingbao on 18-8-29.
 */
public class Trades {
    private static Logger log = Logger.getLogger(Trades.class);
    public State doTrade(Trade trade){
        State state=new State();
        try {
            List list= TradeJdbc.getAll(trade);
            if (list.size()>0){
                state.setState(1);
                state.setData(new Gson().toJson(list));

            }else {
                state.setState(0);
            }

        }catch (Exception e){
            log.error("["+System.currentTimeMillis()+"]Trades doTrade " +
                    "error",e);

        }
        return state;
    }
}
