package mainServer;

import control.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.apache.log4j.Logger;
//https://blog.csdn.net/xiaoduanayu/article/details/78386508
//https://www.cnblogs.com/sessionbest/p/9000727.html

/**
 * Created by jingbao on 18-8-14.
 */
public class OutHandler extends ChannelOutboundHandlerAdapter {
    private static Logger log = Logger.getLogger(OutHandler.class);
    @Override
    // 向client发送消息
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//        Message message= (Message) msg;
//        State state=ServletTest.doServlet(message);
//        String res=new Gson().toJson(state);
//        System.out.println("res-----------");
//        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
//                OK, Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
//        response.headers().set(CONTENT_TYPE, "application/json");
//        response.headers().setInt(CONTENT_LENGTH,
//                response.content().readableBytes());
//        if(state.getServer().equals("doRegister")){
//            response.headers().set(COOKIE,state.getCookies());
//        }
//        ctx.write(response);
//        ctx.flush();
        System.out.println(ctx.channel().isOpen());
        System.out.println(ctx.channel().isActive());
        System.out.println(ctx.channel().isWritable());
        System.out.println(ctx.channel().isRegistered());
          WorkRunable run=new WorkRunable();
          run.setCtx(ctx);
          run.setMessage((Message) msg);
          WorkThreadPool.doWork(run);
    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("********************");
//        //ctx.fireChannelRead(msg);//通知执行下一个InboundHandler执行read
//
//    }


}
