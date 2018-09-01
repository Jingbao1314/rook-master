package control;

import mainServer.WorkThreadPool;
import pojo.Goods;
import pojo.Trade;
import pojo.User;
import util.JdbcMethod.BalanceJdbc;
import util.JdbcMethod.PayJdbc;
import util.JdbcMethod.StoreJdbc;
import util.JdbcMethod.TradeJdbc;
import util.redisutil.RedisOperating;

import java.math.BigDecimal;

/**
 * Created by jingbao on 18-8-23.
 */
public class Pay {
    public State doGet(Goods goods){
        State state=new State();
        Boolean flag= StoreJdbc.select(goods);
        if(flag){
            state.setState(1);
            state.setData(StoreJdbc.get(goods));
        }else {
            state.setState(0);


        }
        return state;
    }
    public State doPay(Goods goods){
        RedisOperating op=new RedisOperating();
        Boolean flag=op.get(goods.getPhone()+"ps").equals(goods.getPhotourl());
        if(!flag){
            System.out.println();
            State state=new State();
            state.setState(2);//密码不对
            return state;
        }
        User user=new User();
        user.setPhone(goods.getPhone().trim());
        System.out.println(user.getPhone()+"---------------------");
        State state= BalanceJdbc.getBalance(user);
        System.out.println(state.getData()+"********************");
        if(state.getData()==null){
            state.setState(0);
            return state;
        }
        BigDecimal balance=new BigDecimal(state.getData());
        State s=BalanceJdbc.getPrice(goods);
        Trade trade=new Trade();
        trade.setDescride(s.getGid());
        String caccount=s.getData();
        trade.setAccount(caccount);
        trade.setPhone(user.getPhone());
        trade.setCid(goods.getId());
        BigDecimal price=new BigDecimal(caccount);
        BigDecimal temp=balance.subtract(price);
//        System.out.println(temp.toString());
        user.setBalance(caccount);
        if(temp.doubleValue()>=0){
            Runnable run=new Runnable() {
                @Override
                public void run() {
                    PayJdbc.update(user);
                    TradeJdbc.inster(trade);

                }
            };
            WorkThreadPool.pool.execute(run);
            state.setData(temp.toEngineeringString());
            state.setState(1);
        }else{
            state.setState(0);
//            state.setData("余额不足");

        }
        return state;

    }

    public static void main(String[] args) {
//            System.out.println(Pay.doGet(new Gson().fromJson
//                ("{id:18222293350__1}",Goods.class)).getState());
    }
}
