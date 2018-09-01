package mysevlete;

import com.google.gson.Gson;
import pojo.Goods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jingbao on 18-6-25.
 */
public class TetsPost {
    public static void main(String[] args) throws IOException, InterruptedException {
//        String url = "http://localhost/8888";
//        //        String json = "{\"head\":{\"qn\":\"mydirectqueue\",\"id\":\"uuid\",\"ty\":0,\"h\":0}}";
//        String json ="{\"id\":0}";
//        final CloseableHttpClient client = HttpClients.createDefault();
//        final HttpPost httpPost;
//        httpPost = new HttpPost(url);
//        httpPost.setEntity(new StringEntity(json, "utf-8"));
//        httpPost.setHeader("Content-type", "application/json");
//
//        //执行请求操作，并拿到结果（同步阻塞）
//
//        try {
//            CloseableHttpResponse response = null;
//            String body = "";
//
//            response = client.execute(httpPost);
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                //按指定编码转换结果实体为String类型
//                body = EntityUtils.toString(entity, "utf-8");
//            }
//            EntityUtils.consume(entity);
//            //释放链接
//            response.close();
//            System.out.println(body);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        URL url = new URL("http://127.0.0.1:8888");
//        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
//        urlConn.setDoOutput(true);
//        urlConn.setDoInput(true);
//        urlConn.setRequestMethod("POST");
//        // 测试内容包
//        String teststr = "this is a test message";
//        OutputStream out = urlConn.getOutputStream();
//        out.write(teststr.getBytes());
//
//
//        out.flush();
//        TimeUnit.SECONDS.sleep(10);//
//                jsonPost("http://127.0.0.1:7721/Login/doLogin",
//                        "17602648414","sdasda");
        for (int i=100;i<101;i++){
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    jsonPost("http://127.0.0.1:7721/Store/doStore",
                            "17602648"+ finalI,"qqqq");
                }
            }).start();
        }

    }
    public static String jsonPost(String strURL,String phone,String name) {
//        System.setProperty("http.keepalive","false");
        try {System.out.println("read  1");
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("","");
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
//            connection.setRequestProperty("Cookies",
//                    "{\"UID\":\"17602648919\"," +
//                    "\"PASSWORD\":\"507721\"}");
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
//            User user=new User();
//            user.setName(name);
//            user.setPassword("507721");
////            user.setBalance("1000");
//            user.setPhone(phone);
//            out.append(new Gson().toJson(user));
//            out.append(new Gson().toJson(new User(
//                    "静宝7721","women", "12345678912")));
            out.append(new Gson().toJson(new Goods("ss","23","",
                    "","4","555","好","","444","13333333333"
                    )));
//            out.append("{\"server\":\"number\",\"number\":\"不对是零二六三8919\"}");
            out.flush();
            out.close();

//            int code = connection.getResponseCode();
//            InputStream is = null;
//            if (code == 200) {
//                is = connection.getInputStream();
//            } else {
//                is = connection.getErrorStream();
//            }
//
//            // 读取响应
//            int length = (int) connection.getContentLength();// 获取长度
//            if (length != -1) {
//                byte[] data = new byte[length];
//                byte[] temp = new byte[512];
//                int readLen = 0;
//                int destPos = 0;
//                while ((readLen = is.read(temp)) > 0) {
//                    System.arraycopy(temp, 0, data, destPos, readLen);
//                    destPos += readLen;
//                }
//                String result = new String(data, "UTF-8"); // utf-8编码
//                return result;
//            }
            System.out.println("read 2");
            BufferedReader read=new BufferedReader(new InputStreamReader
                    (connection.getInputStream()));
            String result="";

            while ((result=read.readLine())!=null){
                System.out.println("read");
                System.out.println(result);



            }
            System.out.println(connection.getHeaderField("Cookie"));
//            System.out.println(connection.getHeaderField("data"));
        } catch (IOException e) {
//            LOG.error("Exception occur when send http post request!", e);
        }
        return "error"; // 自定义错误信息
    }

}
