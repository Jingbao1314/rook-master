package util.JdbcMethod;

import org.apache.log4j.Logger;
import pojo.User;
import util.redisutil.RedisOperating;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by jingbao on 18-8-16.
 */
public class ImproveJdbc {
    private static Logger log = Logger.getLogger(ImproveJdbc.class);
//    public static int improve(User user){
//        log.info("ImproveJdbc.improve:"+user.toString());
//        Connection conn = LoginJdbc.getConn();//Image,Title,Details,Price,
//        // Place,Tel,Date
//        int i = 0;
//        String sql = "insert into  user(id,name,sex,balance,password," +
//                "paypassword,phone) " +
//                "values" +
//                "(?,?," +
//                "?,?,?,?,?);";
//        PreparedStatement pstmt;
//        try {
//            pstmt = (PreparedStatement) conn.prepareStatement(sql);
//            int uid=Integer.parseInt(new RedisOperating().get("uid"));
//            user.setId(uid);
//            pstmt.setInt(1,user.getId());
//            pstmt.setString(2,user.getName());
//            pstmt.setString(3,user.getSex());
//            pstmt.setDouble(4,new BigDecimal(user.getBalance()).doubleValue());
//            pstmt.setString(5,user.getPassword());
//            pstmt.setInt(6,user.getPaypassword());
//            pstmt.setString(7,user.getPhone());
//            i = pstmt.executeUpdate();
//            if(i!=0){
//                uid+=1;
//                RedisOperating oper=new RedisOperating();
//                oper.set(user.getPhone()+"",user.getId()+"");
//                oper.set("uid",uid+"");
//
//            }
//            pstmt.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return i;
//    }
    public static int improve(User user) {
        log.info("ImproveJdbc.improve:"+user.toString());
        Connection conn = LoginJdbc.getConn();
        int i = 0;//,name,sex,balance
        String sql = "update user set paypassword="+"'" + user.getPaypassword()
                +"'"+","+"sex="+"'"+user.getSex()+"'"+ " "+" where " +
                "phone="+"'" +
                user.getPhone() + "';";
        System.out.println(sql);
        // PreparedStatement pstmt;
        try {
            Statement statement=conn.createStatement();
            //  pstmt = (PreparedStatement) conn.prepareStatement(sql);
            // i = pstmt.executeUpdate();
            i=statement.executeUpdate(sql);
            if(i!=0){
                RedisOperating op=new RedisOperating();
                op.set(user.getPhone()+"ps",user.getPaypassword());
            }
            System.out.println("resutl: " + i);
            statement.close();
//            conn.close();
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
}
