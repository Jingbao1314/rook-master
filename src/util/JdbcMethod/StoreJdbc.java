package util.JdbcMethod;

import com.google.gson.Gson;
import control.State;
import org.apache.log4j.Logger;
import pojo.Goods;
import util.redisutil.RedisOperating;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.JdbcMethod.LoginJdbc.getConn;

/**
 * Created by jingbao on 18-7-6.
 */
public class StoreJdbc {
    private static Logger log = Logger.getLogger(StoreJdbc.class);
    public static State insertGoods(Goods goods) {
//        Date date=new Date();
//        Long stime=date.getTime();
////        System.out.println(stime);
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(new Date(stime)));
        State state=new State();
        log.info("StoreJdbc.insertGoods"+goods.toString());
        Connection conn = getConn();//Image,Title,Details,Price,
        // Place,Tel,Date
        int i = 0;
        String sql = "insert into  goods(id,caccount,cdata," +
                "ctype,cdescribe,cstate,photourl,phone,csaleaccount) " +
                "values" +
                "(?,?," +
                "?,?,?,?,?,?,?);";
        PreparedStatement pstmt;
        Boolean flag=false;
        try {
            synchronized (RedisOperating.class){
                RedisOperating oper=new RedisOperating();
                int gid=Integer.parseInt(oper.get("gid"));
//                flag=oper.exists(goods.getId());
                if (goods.getId()!=null){
                    state.setState(2);
                    return state;//物品重复
                }else {
                    String id=goods.getPhone()+"__"+gid;
                    goods.setId(id);
                }
                pstmt = (PreparedStatement) conn.prepareStatement(sql);
                pstmt.setString(1,goods.getId());
                pstmt.setDouble(2,new BigDecimal(goods.getCaccount()).doubleValue());
                pstmt.setString(3, String.valueOf(System.currentTimeMillis()));//sdf.format
                // (new Date
                // (stime))
//                pstmt.setDouble(5,new BigDecimal(goods.getCsaleaccount()).doubleValue());
                pstmt.setString(4,goods.getCtype());
                pstmt.setString(5,goods.getCdescribe());
                pstmt.setString(6,"0");//在售  1 出售  2其他
                pstmt.setString(7,goods.getPhotourl());
                pstmt.setString(8,goods.getPhone());
                pstmt.setDouble(9,new BigDecimal(goods.getCaccount()).doubleValue());
                i = pstmt.executeUpdate();
                if(i==0){
                }else {
                    gid+=1;
                    oper.set("gid",gid+"");
                }
                state.setState(i);
                state.setGid(goods.getId());
                pstmt.close();
//                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("This is error:",e);
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        System.out.println(sql+":"+i);
        return state;
    }


    public static int getId(){
        int res=0;
        Connection conn = getConn();
        String sql = "select count(id) from goods;";
        Statement statement= null;
        try {
            statement = conn.createStatement();
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

    public static Boolean  select(Goods goods){
        String sql="select id FROM goods WHERE id='"+goods.getId()+"';";
        Connection con = getConn();
        System.out.println(sql);
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                System.out.println("");
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;

    }

    public static List getAll(Goods goods) {
        String result="";
        Goods goodsList[]=new Goods[10];
        ArrayList<String> list=new ArrayList<>();
        int flag=0;
        Connection conn = getConn();
        String sql = "select * from goods where phone='"+goods.getPhone()+"';";
        System.out.println(sql);
        try {//dhcp
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()&flag<50) {
                Goods temp=new Goods();
                //news.setTitle(title);
                temp.setId(rs.getString("id"));
                temp.setCtype(rs.getString("ctype"));
                if(rs.getString("cstate")=="0"){
                    temp.setCstate("在售");
                }else if(rs.getString("cstate")=="1"){
                    temp.setCstate("已经出售");
                }else {
                    temp.setCstate("其他");
                }
                if(rs.getString("csaledata")==null){
                    temp.setCsaledata("未出售");
                }else {
                    temp.setCsaledata(rs.getString("csaledata"));
                }
                temp.setCaccount(rs.getString("caccount"));
                temp.setCsaleaccount(rs.getString("csaleaccount"));
                temp.setCdata(rs.getString("cdata"));
                temp.setCdescribe(rs.getString("cdescribe"));
                goodsList[flag]=temp;
                flag++;
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i=0;i<goodsList.length;i++){
            if(!(goodsList[i]==null)){
                //System.out.println(needs[i].getTitle());
                Gson gson=new Gson();
                result=gson.toJson(goodsList[i]);
                list.add(result);
            }
        }
        return list;
    }


    public static String get(Goods goods) {
        String result="";
        Connection conn = getConn();
        String sql = "select * from goods where id='"+goods.getId()+"';";
        System.out.println(sql);
        Goods temp=new Goods();
        try {//dhcp
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                //news.setTitle(title);
                temp.setId(rs.getString("id"));
                temp.setCtype(rs.getString("ctype"));
//                if(rs.getString("cstate")=="0"){
//                    temp.setCstate("在售");
//                }else if(rs.getString("cstate")=="1"){
//                    temp.setCstate("已经出售");
//                }else {
//                    temp.setCstate("其他");
//                }
//                if(rs.getString("csaledata")==null){
//                    temp.setCsaledata("未出售");
//                }else {
//                    temp.setCsaledata(rs.getString("csaledata"));
//                }
                temp.setCaccount(rs.getString("caccount"));
//                temp.setCsaleaccount(rs.getString("csaleaccount"));
//                temp.setCdata(rs.getString("cdata"));
                temp.setCdescribe(rs.getString("cdescribe"));
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Gson().toJson(temp);
    }

    public static void main(String[] args) {
        List list=  StoreJdbc.getAll(new Gson().fromJson
                ("{\"phone\":\"18222293350\"}",Goods.class));
        System.out.println(list.get(0));
    }
}
