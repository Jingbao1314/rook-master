package mainServer;

import com.google.gson.Gson;
import control.Message;
import control.State;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderUtil;
import mysevlete.ServletTest;

import java.io.UnsupportedEncodingException;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by jingbao on 18-8-14.
 */
public class WorkRunable implements Runnable{
    private String result="";
    private Message message=null;
    private ChannelHandlerContext ctx=null;

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        try {
            State state= ServletTest.doServlet(this.message);
            String res=new Gson().toJson(state);
            System.out.println("res-----------");
            FullHttpResponse response = null;
            response = new DefaultFullHttpResponse(HTTP_1_1,
                    OK, Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
            response.headers().set(CONTENT_TYPE, "application/json");
            response.headers().setInt(CONTENT_LENGTH,
                    response.content().readableBytes());
            response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN,"*");
            if (HttpHeaderUtil.isKeepAlive(this.message.getFhr())) {
                response.headers().set(CONNECTION, KEEP_ALIVE);
            }
//            if(state.getServer().equals("doRegister")){
//                response.headers().set(COOKIE,state.getCookies());
//            }
            this.ctx.write(response);
            this.ctx.flush();


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
