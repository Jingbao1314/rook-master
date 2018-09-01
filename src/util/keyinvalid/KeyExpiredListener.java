package util.keyinvalid;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import util.madvice.SendMsg;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by jingbao on 18-8-7.
 */
public class KeyExpiredListener extends JedisPubSub {
    private static Logger log = Logger.getLogger(KeyExpiredListener.class);
    public static String [] messageList=new String[]{"您好,您的商品已存放一天,降价已生效"};
//    private String key="";
//    public KeyExpiredListener(String key){
//        this.key=key;
//
//    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe "
                + pattern + " " + subscribedChannels);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {


        System.out.println("onPMessage pattern "
                + pattern + " " + channel + " " + message);
        Jedis jedis=new Jedis("127.0.0.1",6379);
        String par=String.valueOf(System.currentTimeMillis()).substring(0,9)
                +"*";
        Set s=jedis.keys(par);
        Iterator iterator=s.iterator();
        while (iterator.hasNext()){
            String phone=jedis.get((String) iterator.next());
            String smassage="验证码:8888";
            try {
                SendMsg.send(smassage,phone);
            } catch (Exception e) {
                log.error("信息发送异常:",e);
                e.printStackTrace();
            }
//            System.out.println(jedis.get((String) iterator.next()));
        }
//        System.out.println(jedis.exists(String.valueOf(System.currentTimeMillis())));
    }



}
