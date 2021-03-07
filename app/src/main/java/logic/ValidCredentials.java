package logic;

import data.UserDB;
import flashcard.group5.application.MainActivity;
import interfaces.IValidCredentials;
import objects.User;

public class ValidCredentials implements IValidCredentials {

    private User user;
    private UserDB userDB;

    public ValidCredentials(User user){
        this.user = user;
        this.userDB = MainActivity.getUserDB();
    }

    public boolean userExists() {
        return this.userDB.userExists(this.user);
    }
}
