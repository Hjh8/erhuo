package entity;

public class User {
    private String id;
    private String name;
    private String phone;
    private String realName;
    private String sno;
    private String drom;
    private String gender;
    private String avatar;
    private String pwd;
    private Integer sta;
    private String createTime;
    private String token; // 用户token

    public User(){}

    public User(String id, String name, String phone, String realName, String sno,
                String drom, String gender, String avatar, String pwd, Integer sta, String createTime) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.realName = realName;
        this.sno = sno;
        this.drom = drom;
        this.gender = gender;
        this.avatar = avatar;
        this.pwd = pwd;
        this.sta = sta;
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getDrom() {
        return drom;
    }

    public void setDrom(String drom) {
        this.drom = drom;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getSta() {
        return sta;
    }

    public void setSta(Integer sta) {
        this.sta = sta;
    }
}
