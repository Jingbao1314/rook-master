package control;

import pojo.User;
import util.JdbcMethod.BalanceJdbc;

/**
 * Created by jingbao on 18-8-23.
 */
public class Balance {
    public State doBalance(User user){
        State state= BalanceJdbc.getBalance(user);
        if(state.getData()==null){
            state.setData("0.0");
        }
        return state;

    }
}
