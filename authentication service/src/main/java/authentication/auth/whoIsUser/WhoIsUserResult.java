package authentication.auth.whoIsUser;

/**
 * Created by jongzazaal on 18/3/2560.
 */
public class WhoIsUserResult {

    private int user_id, dep_id;
    private String user_username,user_fname, user_lname, dep_name;

    public WhoIsUserResult(int user_id, String user_username, String user_fname, String user_lname, int dep_id, String dep_name) {
        this.user_id = user_id;
        this.user_username = user_username;
        this.user_fname = user_fname;
        this.user_lname = user_lname;
        this.dep_id = dep_id;
        this.dep_name = dep_name;
    }

    public WhoIsUserResult() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getUser_fname() {
        return user_fname;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    public String getUser_lname() {
        return user_lname;
    }

    public void setUser_lname(String user_lname) {
        this.user_lname = user_lname;
    }

    public int getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }
}
