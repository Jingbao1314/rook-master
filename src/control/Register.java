package control;

import pojo.User;
import util.JdbcMethod.RegisterJdbc;

/**
 * Created by jingbao on 18-7-25.
 */
public class Register {
    public State doRegister(User user){
        State status=new State();
        int state= RegisterJdbc.register(user);
        if(state==0){
            status.setState(0);
        }else {
            status.setState(1);
        }
        return status;
    }
}
