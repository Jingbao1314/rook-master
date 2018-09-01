package util.JdbcMethod;

import com.google.gson.Gson;
import pojo.Trade;
import util.redisutil.RedisOperating;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.JdbcMethod.LoginJdbc.getConn;

/**
 * Created by jingbao on 18-8-29.
 */
public class TradeJdbc {
    public static List getAll(Trade trade) {
        String result="";
        Trade tradesList[]=new Trade[10];
        ArrayList<String> list=new ArrayList<>();
        int flag=0;
        Connection conn = getConn();
        String sql = "select * from trade where phone='"+trade.getPhone()+"';";
        System.out.println(sql);
        try {//dhcp
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()&flag<50) {
                Trade temp=new Trade();
                //news.setTitle(title);
//                temp.setCid(rs.getString("cid"));
                temp.setAccount(rs.getString("account"));
                temp.setDescride(rs.getString("descride"));
                temp.setDate(rs.getString("data"));
                tradesList[flag]=temp;
                flag++;
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i=0;i<tradesList.length;i++){
            System.out.println();
            if(!(tradesList[i]==null)){
                Gson gson=new Gson();
                result=gson.toJson(tradesList[i]);
                list.add(result);
            }
        }
        return list;
    }

    public static int inster(Trade trade){
        Connection conn = LoginJdbc.getConn();//Image,Title,Details,Price,
        // Place,Tel,Date
        int i = 0;
        String sql = "insert into trade(data, account," +
                "phone,descride,cid) " +
                "values" +
                "(?,?," +
                "?,?,?);";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            synchronized (RedisOperating.class){
                pstmt.setString(1,String.valueOf(System.currentTimeMillis()));
                pstmt.setString(2,trade.getAccount());
                pstmt.setString(3,trade.getPhone());
                pstmt.setString(4,trade.getDescride());
                pstmt.setString(5,trade.getCid());
                i = pstmt.executeUpdate();
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
}
