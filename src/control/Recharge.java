package control;

import pojo.User;
import util.JdbcMethod.RechargeJdbc;

/**
 * Created by jingbao on 18-8-16.
 */
public class Recharge {
    public State doRecharge(User user){
        State status=new State();
        int state= RechargeJdbc.recharge(user);
        if(state==0){
            status.setState(0);
        }else {
            status.setState(1);
        }
        return status;
    }
}
