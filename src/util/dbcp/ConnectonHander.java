package util.dbcp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jingbao on 18-8-18.
 */
public class ConnectonHander implements InvocationHandler {
    private Object con;
    public Object bind(Object conn){
        con=  conn;
        return Proxy.newProxyInstance(conn.getClass().getClassLoader(),
                conn.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("close")){
            System.out.println( "被还给Connections数据库连接池了！！");

        }
        return null;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/share";
        String username = "root";
        String password = "507721";

        //加载数据库驱动
        Class.forName(driver);

        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println("获取到了链接" + conn);


        Connection con= (Connection) new ConnectonHander().bind(conn);
        con.close();
    }
}
