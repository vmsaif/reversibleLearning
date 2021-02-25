package objects;
import interfaces.IProfile;

public class Profile implements IProfile{

    //variables
    private String user_name;
    private String password;

    //constructor
    public Profile(String user_name, String password){
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
