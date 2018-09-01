package util.madvice;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * Created by jingbao on 18-8-7.
 */
public class SendMsg {
    public static void send(String message,String phone)throws Exception
    {

        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://utf8.api.smschinese.cn");
        post.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
        NameValuePair[] data ={ new NameValuePair("Uid", "jingbao507721"),new
                NameValuePair("Key", "d41d8cd98f00b204e980"),new
                NameValuePair("smsMob",phone),new NameValuePair
                ("smsText",message)};
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode);
        for(Header h : headers)
        {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes
                ("utf8"));
        System.out.println(result); //打印返回消息状态


        post.releaseConnection();

    }

}
