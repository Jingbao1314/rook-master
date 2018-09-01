package util.JdbcMethod;

import control.State;
import org.apache.log4j.Logger;
import pojo.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by jingbao on 18-8-16.
 */
public class RechargeJdbc {
    private static Logger log = Logger.getLogger(ImproveJdbc.class);
    public static int recharge(User user) {
        log.info("RechargeJdbc.recharge:"+user.toString());
        Connection conn = LoginJdbc.getConn();
        State state=BalanceJdbc.getBalance(user);
//        String dbMoney=state.getData();
//        String money=user.getBalance();
        BigDecimal dbMoney=null;
        BigDecimal money=new BigDecimal(user.getBalance());;
        if(state.getData()==null){
            user.setBalance(money.toEngineeringString());

        }else {
            dbMoney=new BigDecimal(state.getData());

            user.setBalance(money.add(dbMoney).toEngineeringString());

        }
        int i = 0;//,name,sex,balance
        String sql = "update user set balance="+"'" + user.getBalance()
                +"'"+" where " +
                "phone="+"'" +
                user.getPhone() + "';";
        System.out.println(sql);
        // PreparedStatement pstmt;
        try {
            Statement statement=conn.createStatement();
            i=statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return i;
    }

    public static void main(String[] args) {
        BigDecimal dbMoney=new BigDecimal("500");
        BigDecimal money=new BigDecimal("100");
        BigDecimal xx=null;
        xx=money.add(dbMoney);
        System.out.println(money.add(dbMoney).toEngineeringString());
        System.out.println(money.add(dbMoney).toPlainString());
        System.out.println(money.add(dbMoney).toString());
    }
}
