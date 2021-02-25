package objects;
import interfaces.IUser;

public class User implements IUser {

    //variables
    private String user_name;
    private String password;

    //constructor
    public User(String user_name, String password){
        this.user_name = user_name;
        this.password = password;
    }


    public void changeUserName(String user_name) {
        this.user_name = user_name;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public String getUserName(){ return this.user_name; }

    public String getPassword() { return this.password; }
}
