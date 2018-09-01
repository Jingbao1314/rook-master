package pojo;

/**
 * Created by jingbao on 18-7-5.
 */
public class User {
//    Field       | Type         | Null | Key | Default | Extra |
//            +-------------+--------------+------+-----+---------+-------+
//            | id          | int(11)      | NO   | PRI | NULL    |       |
//            | name        | varchar(12)  | NO   |     | NULL    |       |
//            | sex         | varchar(12)  | YES  |     | NULL    |       |
//            | balance     | double(16,2) | YES  |     | NULL    |       |
//            | password    | varchar(12)  | YES  |     | NULL    |       |
//            | paypassword | int(11)      | YES  |     | NULL    |       |
//            | phone       | int(11)      | YES  |     | NULL    |       |
//            +-------------+--------------+------+-----+---------+-------+

    private int id;
    private String name;
    private String sex;
    private String balance;
    private String password;
    private String paypassword;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPaypassword() {
        return paypassword;
    }

    public void setPaypassword(String paypassword) {
        this.paypassword = paypassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "{id:"+this.id+",name:"+this.name+",sex:"+this.sex+",balance:"+
                this.balance+",password:"+this.balance+",paypassword:"+this
                .paypassword+",phone:"+this.phone+"}";
    }

    public User(int id, String name, String sex, String balance, String
            password, String paypassword, String phone) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.balance = balance;
        this.password = password;
        this.paypassword = paypassword;
        this.phone = phone;
    }

    public User(String password, String phone) {
        this.password = password;
        this.phone = phone;
    }

    public User(String name, String sex, String phone) {
        this.name = name;
        this.sex = sex;
        this.phone = phone;
    }

    public User() {
    }

}
