package userinfo.userInfo;

/**
 * Created by jongzazaal on 6/3/2560.
 */
public class UserInfo {
    private String user_firstname_th, user_lastname_th, user_firstname_en, user_lastname_en, user_username, user_password;
    private int user_id, user_department;

    public UserInfo(){}

    public UserInfo(String user_firstname_th, String user_lastname_th, String user_firstname_en,String user_password,
                    String user_lastname_en, String user_username, int user_department, int user_id) {
        this.user_firstname_th = user_firstname_th;
        this.user_lastname_th = user_lastname_th;
        this.user_firstname_en = user_firstname_en;
        this.user_lastname_en = user_lastname_en;
        this.user_username = user_username;
        this.user_department = user_department;
        this.user_id = user_id;
    }

    public String getUser_firstname_th() {
        return user_firstname_th;
    }

    public String getUser_lastname_th() {
        return user_lastname_th;
    }

    public String getUser_firstname_en() {
        return user_firstname_en;
    }

    public String getUser_lastname_en() {
        return user_lastname_en;
    }

    public String getUser_username() {
        return user_username;
    }

    public String getUser_password() {
        return user_password;
    }

    public int getUser_department() {
        return user_department;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_firstname_th(String user_firstname_th) {
        this.user_firstname_th = user_firstname_th;
    }

    public void setUser_lastname_th(String user_lastname_th) {
        this.user_lastname_th = user_lastname_th;
    }

    public void setUser_firstname_en(String user_firstname_en) {
        this.user_firstname_en = user_firstname_en;
    }

    public void setUser_lastname_en(String user_lastname_en) {
        this.user_lastname_en = user_lastname_en;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setUser_department(int user_department) {
        this.user_department = user_department;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
