package mainServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jingbao on 18-8-14.
 */
public class WorkThreadPool {
    public static ExecutorService pool= Executors.newCachedThreadPool();
    public static void doWork(Runnable runnable){
        pool.execute(runnable);
//        pool.submit(runnable);

    }

    public static void main(String[] args) {
       for (int i=0;i<3;i++){
           Runnable runnable=new Runnable() {
               @Override
               public void run() {
                   System.out.println("POLL");
               }
           };
           pool.execute(runnable);
       }
//        pool.shutdown();
//        pool.submit();
    }
}
