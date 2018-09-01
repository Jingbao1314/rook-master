package control;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import pojo.Goods;
import util.JdbcMethod.StoreJdbc;
import util.redisutil.RedisOperating;

import java.util.List;

/**
 * Created by jingbao on 18-6-23.
 */
public class Store {
    private static Logger log = Logger.getLogger(Store.class);

    public State doStore(Goods goods){
        State state=new State();
        try {
            state= StoreJdbc.insertGoods(goods);
            if(state.getState()!=0){
                RedisOperating operating=new RedisOperating();
                System.out.println();
                String id="gid_"+operating.get("gid");
                operating.set(id,goods.getPhone());
                operating.expire(id, 5);
                String time=String.valueOf(System.currentTimeMillis()+5000);
                operating.set(time,goods.getPhone());

            }
        }catch (Exception e){
            log.error("["+System.currentTimeMillis()+"]Store doStore error",e);

        }
        return state;
    }

    public State doChange(Goods goods){
        State state=new State();
        try {
            state= StoreJdbc.insertGoods(goods);
            if(state.getState()!=0){
                RedisOperating operating=new RedisOperating();
                String id="gid_"+operating.get("gid");
                operating.set(id,goods.getPhone());
                operating.expire(id, 5);
                String time=String.valueOf(System.currentTimeMillis()+5000);
                operating.set(time,goods.getPhone());

            }
        }catch (Exception e){
            log.error("["+System.currentTimeMillis()+"]Store doChange " +
                    "error",e);

        }
        return state;
    }

    public State doGet(Goods goods){
        State state=new State();
        try {
            List list= StoreJdbc.getAll(goods);
            if (list.size()>0){
                state.setState(1);
                state.setData(new Gson().toJson(list));
                System.out.println(state.getData());

            }else {
                state.setState(0);
            }

        }catch (Exception e){
            log.error("["+System.currentTimeMillis()+"]Store doGet " +
                    "error",e);

        }
        return state;
    }

//    public static void main(String[] args) {
//        System.out.println(System.currentTimeMillis());//1534172850943
//
//    }


}