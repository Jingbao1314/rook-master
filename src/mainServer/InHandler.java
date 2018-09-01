package mainServer;

import control.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import org.apache.log4j.Logger;
//
//import static io.netty.handler.codec.http.HttpHeaderNames.*;
//import static io.netty.handler.codec.http.HttpResponseStatus.OK;
//import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
//https://blog.csdn.net/xiaoduanayu/article/details/78386508

/**
 * Created by jingbao on 18-6-23.
 */
public class InHandler extends ChannelInboundHandlerAdapter{
    private static Logger log = Logger.getLogger(InHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        log.info("InHandler :" + ctx);
        Message message=new Message();
        System.out.println("read");
        try {
//            System.out.println(msg.toString());
            FullHttpRequest fhr = (FullHttpRequest) msg;
            System.out.println(fhr.headers().get("Cookies"));
            message.setCookies((String) fhr.headers().get("Cookies"));
//            System.out.println(cookie.toString());
//            ByteBuf result = (ByteBuf) msg;
//            byte[] result1 = new byte[result.readableBytes()];
//            result.readBytes(result1);
            System.out.println("请求的URL："+fhr.uri());
            message.setUrl(fhr.uri());
            message.setFhr(fhr);
            ByteBuf buf = fhr.content();
            HttpHeaders head=fhr.headers();
//            System.out.println( head.toString());
            byte[] result1 = new byte[buf.readableBytes()];
            buf.readBytes(result1);
            String data=new String(result1,"utf8");
            System.out.println("读取的数据："+data);
            message.setData(data);
//            ServletTest.doServlet(fhr.uri(),new
//                    Gson().fromJson(data,Goods.class));



//            ByteBuf buf = fhr.content();
//            String message = buf.toString(io.netty.util.CharsetUtil.UTF_8);
//            buf.release();
//            String tt =ServletTest.doServlet(fhr.uri(),data);
//            System.out.println("res-----------");
//            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
//                    OK, Unpooled.wrappedBuffer(tt.getBytes("UTF-8")));
//            response.headers().set(CONTENT_TYPE, "application/json");
//            response.headers().setInt(CONTENT_LENGTH,
//                    response.content().readableBytes());
//            if (HttpHeaderUtil.isKeepAlive(fhr)) {
//                response.headers().set(CONNECTION, KEEP_ALIVE);
//            }
//            ctx.write(response);
//            ctx.flush();
            ctx.write(message);
            System.out.println("write");
//            int a=5/0;
        }catch (Exception e){
            log.error("",e);
            e.printStackTrace();
        }
        //log.debug("--------------------------------------");
    }
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//
//    }


}