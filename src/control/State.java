package control;

/**
 * Created by jingbao on 18-7-4.
 */
public class State {
//    private String server="";
    private int state=7721;
    private String gid="";
    private String data="";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    //    private String cookies="";

//    public String getServer() {
//        return server;
//    }
//
//    public void setServer(String server) {
//        this.server = server;
//    }
//
//    public String getCookies() {
//        return cookies;
//    }
//
//    public void setCookies(String cookies) {
//        this.cookies = cookies;
//    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
