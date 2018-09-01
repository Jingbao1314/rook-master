package util.JdbcMethod;

import control.State;
import pojo.Goods;
import pojo.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by jingbao on 18-8-23.
 */
public class BalanceJdbc {
    public static State getBalance(User user){
        State state=new State();
        Connection conn = LoginJdbc.getConn();
        String sql = "select balance from user WHERE phone="+"'"+user
                .getPhone()+"';";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                state.setData(rs.getString("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return state;

    }

    public static State getPrice(Goods goods){
        State state=new State();
        Connection conn = LoginJdbc.getConn();
        String sql = "select caccount,cdescribe from goods WHERE id="+"'"+goods
                .getId()+"';";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                state.setData(rs.getString("caccount"));
                state.setGid(rs.getString("cdescribe"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return state;

    }
}
