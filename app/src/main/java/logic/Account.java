package logic;

import android.util.Log;

import data.UserPersistence;
import flashcard.group5.application.Services;
import interfaces.IAccount;
import objects.User;

public class Account implements IAccount {
    UserPersistence userPersistence;
    ValidCredentials validCredentials;

    public Account() {
        userPersistence = Services.getUserPersistence();
        this.validCredentials = new ValidCredentials();
    }

    public Account(final UserPersistence userPersistence){
        this();
        this.userPersistence = userPersistence;
    }

    public boolean addNewAccount(String userName, String password) {
       boolean result = !validCredentials.userNameTaken(userName);

        if(result) {
            userPersistence.insertUser(new User(userName, password));
        }
        return result;
    }
}
