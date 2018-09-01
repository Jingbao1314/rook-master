package pojo;

/**
 * Created by jingbao on 18-6-23.
 */
public class Goods {
    private String id;
    private String caccount;
    private String cdata;
    private String csaledata;
    private String csaleaccount;
    private String ctype;
    private String cdescribe;
    private String cstate;
    private String photourl;
    private String phone;
    public Goods(){}

    public String getCdata() {
        return cdata;
    }

    public void setCdata(String cdata) {
        this.cdata = cdata;
    }

    public String getCsaledata() {
        return csaledata;
    }

    public void setCsaledata(String csaledata) {
        this.csaledata = csaledata;
    }

    public void setCdescribe(String cdescribe) {
        this.cdescribe = cdescribe;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getCdescribe() {
        return cdescribe;
    }
//
//    public void setCdescride(String cdescribe) {
//        this.cdescribe = cdescribe;
//    }

    public String getCstate() {
        return cstate;
    }

    public void setCstate(String cstate) {
        this.cstate = cstate;
    }

    public String getCaccount() {
        return caccount;
    }

    public void setCaccount(String caccount) {
        this.caccount = caccount;
    }

    public String getCsaleaccount() {
        return csaleaccount;
    }

    public void setCsaleaccount(String csaleaccount) {
        this.csaleaccount = csaleaccount;
    }

    public Goods(String id, String caccount, String cdata, String csaledata,
                 String csaleaccount, String ctype, String cdescribe, String
                         cstate, String photourl,String phone) {
        this.id = id;
        this.caccount = caccount;
        this.cdata = cdata;
        this.csaledata = csaledata;
        this.csaleaccount = csaleaccount;
        this.ctype = ctype;
        this.cdescribe = cdescribe;
        this.cstate = cstate;
        this.photourl = photourl;
        this.phone=phone;
    }
    public Goods(String caccount, String cdata, String csaledata,
                 String csaleaccount, String ctype, String cdescribe, String
                         cstate, String photourl,String phone) {
        this.id = "17602648919_7721";
        this.caccount = caccount;
        this.cdata = cdata;
        this.csaledata = csaledata;
        this.csaleaccount = csaleaccount;
        this.ctype = ctype;
        this.cdescribe = cdescribe;
        this.cstate = cstate;
        this.photourl = photourl;
        this.phone=phone;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id='" + id + '\'' +
                ", caccount='" + caccount + '\'' +
                ", cdata=" + cdata +
                ", csaledata=" + csaledata +
                ", csaleaccount='" + csaleaccount + '\'' +
                ", ctype='" + ctype + '\'' +
                ", cdescribe='" + cdescribe + '\'' +
                ", cstate='" + cstate + '\'' +
                ", photourl='" + photourl + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
