package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jingbao on 18-7-5.
 */
public class Test {
    public static void main(String[] args) {
        Date date=new Date();
        Long stime=date.getTime();
        System.out.println(stime);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date(stime)));
    }
}
