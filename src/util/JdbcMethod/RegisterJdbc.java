package util.JdbcMethod;

import org.apache.log4j.Logger;
import pojo.User;
import util.redisutil.RedisOperating;

import java.sql.*;

/**
 * Created by jingbao on 18-7-25.
 */
public class RegisterJdbc {
    private static Logger log = Logger.getLogger(RegisterJdbc.class);
//    public static Boolean  select(String sql){
//        Connection conn = LoginJdbc.getConn();
//        log.info("LoginJdbc.select");
//        System.out.println(sql);
//        try {
//            Statement statement = conn.createStatement();
//            ResultSet rs = statement.executeQuery(sql);
//            if(rs.next()){
//                return true;
//            }else {
//                return false;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//
//    }


    public static int getId(){
        int res=0;
        Connection conn = LoginJdbc.getConn();
        String sql = "select count(id) from user;";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                res = rs.getInt(1)+res;
            }

//            System.out.println(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
    public static int register(User user){
        log.info("Register.register"+user.toString());
        Connection conn = LoginJdbc.getConn();//Image,Title,Details,Price,
        // Place,Tel,Date
        int i = 0;
        String sql = "insert into  user(id,password," +
                "phone,name) " +
                "values" +
                "(?,?," +
                "?,?);";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            synchronized (RedisOperating.class){
                int uid=Integer.parseInt(new RedisOperating().get("uid"));
                user.setId(uid);
                pstmt.setInt(1,user.getId());
                pstmt.setString(2,user.getPassword());
                pstmt.setString(3,user.getPhone());
                pstmt.setString(4,user.getName());
                i = pstmt.executeUpdate();
                if(i!=0){
                    uid+=1;
                    RedisOperating oper=new RedisOperating();
//                    System.out.println();
                    oper.set(user.getPhone(),user.getPassword());
                    oper.set("uid",uid+"");

                }
                pstmt.close();
//                conn.close();
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
        return i;
    }
    public static int improve(User user){

        return 1;

    }
}
