package util.dbcp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;


/**
 * Created by jingbao on 18-8-18.
 */
public class JdbcPool {
    /**
     * @Field: abqcon
     *         使用ArrayBlockingQueue来存放数据库链接，
     */
    private static ArrayBlockingQueue abqcon = new ArrayBlockingQueue(200);
    public static int a=10;
    static{

        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/share?useUnicode=true&characterEncoding=utf-8";
            String username = "root";
            String password = "507721";
            //数据库连接池的初始化连接数大小
            int jdbcPoolInitSize =100;
            //加载数据库驱动
            Class.forName(driver);
            for (int i = 0; i < jdbcPoolInitSize; i++) {
                Connection conn = DriverManager.getConnection(url, username, password);
                System.out.println("获取到了链接" + conn+"-------"+i);
                //将获取到的数据库连接加入到Connections集合中，Connections此时就是一个存放了数据库连接的连接池
                abqcon.put(conn);
            }

        } catch (SQLException e) {
            System.out.println(" 创建数据库连接失败！ " + e.getMessage());
            try {
                throw new SQLException();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public synchronized static Object get(){
        //如果数据库连接池中的连接对象的个数大于0
        //从abqcon中获取一个数据库连接

            Connection conn;
            Object con= null;
            try {
                conn= (Connection) abqcon.take();
                System.out.println("Connections数据库连接池大小是" + abqcon.size());
                return Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), conn.getClass().getInterfaces(), new InvocationHandler(){
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        if(!method.getName().equals("close")){
                            return method.invoke(conn, args);
                        }else{
                            //如果调用的是Connection对象的close方法，就把conn还给数据库连接池
                            abqcon.put(conn);
                            System.out.println(conn + "被还给Connections数据库连接池了！！");
                            System.out.println("Connections数据库连接池大小为" + abqcon.size());
                            return null;
                        }
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


//        try {
//            con = abqcon.poll(8, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if(con==null){
//            return null;
//        }else {
//            conn= (Connection) con;
//        }
        return null;


    }
}
