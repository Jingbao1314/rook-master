package control;

import pojo.User;
import util.JdbcMethod.BalanceJdbc;
import util.JdbcMethod.ImproveJdbc;

/**
 * Created by jingbao on 18-8-16.
 */
public class Improve {
    public State doImprove(User user){
        State status=new State();
        int state= ImproveJdbc.improve(user);
        if(state==0){
            status.setState(0);
        }else {
            status.setState(1);
        }
        return status;
    }

    public State doBalance(User user){
        State state= BalanceJdbc.getBalance(user);
        if(state.getData()==null){
            state.setData("0.0");
        }
        return state;

    }
}
