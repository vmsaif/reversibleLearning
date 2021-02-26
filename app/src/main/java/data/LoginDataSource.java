package data;

import flashcard.group5.application.MainActivity;
import interfaces.ILoginDataSource;
import objects.User;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource implements ILoginDataSource {

    public Result<User> login(String username, String password) {
        try {
            UserDB userDB = MainActivity.getUserDB();
            User currUser = new User(username, password);

            if(userDB.userExits(currUser))

            // TODO: handle loggedInUser authentication
         /*   LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");*/
            return new Result.Success<>(currUser);
            else return new Result.Error(new IOException("User not found"));
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
