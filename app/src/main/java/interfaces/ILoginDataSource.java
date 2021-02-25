package interfaces;

import data.Result;
import data.model.LoggedInUser;

public interface ILoginDataSource {

    //checks if the user logged in or not
    Result<LoggedInUser> login(String username, String password);

    // revoke authentication
    void logout();
}
