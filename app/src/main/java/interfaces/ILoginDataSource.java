package interfaces;

import data.Result;
import objects.User;

public interface ILoginDataSource {

    //checks if the user logged in or not
    Result<User> login(String username, String password);

    // revoke authentication
    void logout();
}
