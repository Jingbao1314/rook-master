package util.cookie;

import com.google.gson.Gson;

/**
 * Created by jingbao on 18-8-14.
 */
class Cookie{
    private String phone="";
    private String psw="";

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public Cookie(String phone, String psw) {
        this.phone = phone;
        this.psw = psw;
    }
}
public class MyCookies {
    public static String getCookies(String phone,String psw){
        return new Gson().toJson(new Cookie(phone,psw));

    }
}
