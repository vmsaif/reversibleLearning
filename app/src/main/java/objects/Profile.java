package objects;

public class Profile {

    //variables
    private String first_name;
    private String last_name;
    private String user_name;
    private String password;

    //constructor
    public Profile(String first_name, String last_name, String user_name, String password){
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_name = user_name;
        this.password = password;
    }

    public void changefName(String first_name) {
        this.first_name = first_name;
    }

    public void changelName(String last_name) {
        this.last_name = last_name;
    }

    public void changeUserName(String user_name) {
        this.user_name = user_name;
    }

    public void changePassword(String password) {
        this.password = password;
    }
}
