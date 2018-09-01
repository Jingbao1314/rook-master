package util.JdbcMethod;

import control.State;
import org.apache.log4j.Logger;
import pojo.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by jingbao on 18-8-25.
 */
public class PayJdbc {
    private static Logger log = Logger.getLogger(ImproveJdbc.class);
    public static int update(User user) {
        log.info("PayJdbc.recharge:"+user.toString());
        Connection conn = LoginJdbc.getConn();
        State state=BalanceJdbc.getBalance(user);
//        String dbMoney=state.getData();
//        String money=user.getBalance();
        BigDecimal dbMoney=new BigDecimal(state.getData());
        BigDecimal money=new BigDecimal(user.getBalance());
        user.setBalance(dbMoney.subtract(money).toEngineeringString());

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
                log.error("error ",e);
                e.printStackTrace();
            }

        }
        return i;
    }
}
