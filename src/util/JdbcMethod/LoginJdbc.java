package util.JdbcMethod;

import org.apache.log4j.Logger;
import pojo.User;
import util.dbcp.JdbcPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by jingbao on 18-7-5.
 */
public class LoginJdbc {//登录
    private static Logger log = Logger.getLogger(LoginJdbc.class);
    public static Connection getConn() {
        Connection con=null;
        con=(Connection)  JdbcPool.get();
        return con;
    }

    public static Boolean  select(User user){
        log.info("LoginJdbc.select"+user.toString());
        String sql="select phone FROM user WHERE phone='"+user.getPhone()+"';";
        Connection conn = getConn();
        System.out.println(sql);
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
           if(rs.next()){
//               conn.close();
               return true;
           }else {
//               conn.close();
               return false;
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
        return false;

    }

//    public static int insert(User user){
//        log.info("StoreJdbc.insertGoods");
//        Connection conn = LoginJdbc.getConn();//Image,Title,Details,Price,
//        // Place,Tel,Date
//        int i = 0;
//        String sql = "insert into  user(uname,upsw,tpsw,uid) values" +
//                "(?,?," +
//                "?,?);";
//        PreparedStatement pstmt;
//        try {
//            pstmt = (PreparedStatement) conn.prepareStatement(sql);
//            pstmt.setString(1,user.getUname());
//            pstmt.setString(2,user.getUpsw());
//            pstmt.setString(3,user.getTpsw());
//            pstmt.setString(4,user.getUid());
//            i = pstmt.executeUpdate();
//            pstmt.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return i;
//    }

}
