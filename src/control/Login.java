package control;

import org.apache.log4j.Logger;
import pojo.User;
import util.JdbcMethod.LoginJdbc;
import util.redisutil.RedisOperating;

/**
 * Created by jingbao on 18-7-15.
 */
public class Login {
    private static Logger log = Logger.getLogger(Login.class);


    public int doLogin(User user){
        log.info("this is Login"+user.toString());
        int flag=0;
        RedisOperating oper=new RedisOperating();
        if(oper.exists(user.getPhone())){
            flag=1;//成功

        }else {
            if((LoginJdbc.select(user))){
                System.out.println("redis login");
                oper.set(user.getPhone(),user.getPassword(),30*60);
//            if ()
                flag= 1;//成功

            }else {
                flag= 0;//失败
            }
        }
//        jedis.lpush("1","2");
//        jedis.lpush("1","1");
//        jedis.lpush("1","0");
//        String sql=" select uid,upsw from user where " +
//                "uid='"+user.getUid()+"' " +
//                "and upsw='"+user.getUpsw()+"';";
//        Boolean flag= LoginJdbc.select(sql);
//        if (!flag){
//            int fleg=LoginJdbc.insert(user);
//            System.out.println(fleg+"-+-+--+--+-+-+-+-");
//            return fleg;
//        }else{
//            return 2;//用户已经存在
//            //drbd
//        }
        return flag;

    }
}
