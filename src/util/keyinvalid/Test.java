package util.keyinvalid;

import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jingbao on 18-8-7.
 */
public class Test {


    public static void run(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.set("xx", "你还在吗");
        jedis.expire("xx", 3);
        String time=String.valueOf(System.currentTimeMillis()+3000);
        jedis.set(time,"17602648919");
    }

    public static void main(String[] args) throws ParseException {

//        jedis.expireAt("notify", System.currentTimeMillis()+10000);
//        System.out.println(jedis.get("notify"));
//        Jedis jedis=new Jedis("127.0.0.1",6379);
//        System.out.println();
//        Jedis jedis=new Jedis("127.0.0.1",6379);
//        jedis.set("2018-02-02","sad");
//
//        Set s=jedis.keys("");
//        System.out.println(s.isEmpty());
//        System.out.println(String.valueOf(System.currentTimeMillis())
//                .substring(0,9));//1533620903512


//
//        long a=Long.parseLong(dateToStamp("2018-08-07 " +
//                "12:43:00"))+1000;
//        System.out.println(a);
//        System.out.println(stampToDate(String.valueOf(a)));
        run();


    }
    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
