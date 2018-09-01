package pojo;

/**
 * Created by jingbao on 18-7-24.
 */
public class Trade {
    private String phone;
    private String cid;
    private String account;
    private String date;
    private String descride;

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    private String photourl;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescride() {
        return descride;
    }

    public void setDescride(String descride) {
        this.descride = descride;
    }

    public Trade(String phone, String cid, String account, String date, String descride) {
        this.phone = phone;
        this.cid = cid;
        this.account = account;
        this.date = date;
        this.descride = descride;
    }

    public Trade() {
    }


}
