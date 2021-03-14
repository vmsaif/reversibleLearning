package logic;

import android.util.Log;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import data.UserPersistence;
import data.hsqldb.UserDB;
import flashcard.group5.application.MainActivity;
import flashcard.group5.application.Services;
import interfaces.IValidCredentials;
import objects.User;
import android.widget.Toast;

public class ValidCredentials implements IValidCredentials {

    private User user;

    private UserPersistence userPersistence;
    private List<User> users;

    public ValidCredentials() {
        userPersistence = Services.getUserPersistence();
        users = null;
        user = null;
    }

    public ValidCredentials(final UserPersistence userPersistence){
        this();
        this.userPersistence = userPersistence;
    }


    @Override
    public List<User> getUsers() {
        users = userPersistence.getUserSequential();
        return Collections.unmodifiableList(users);
    }

    @Override
    public User getUser(String userName, String password) {
        user = null;
        boolean found = false;
        //will get a list of all users in the database, then do a search for the user
        users = getUsers();
        int i = 0; //iterator through users list

        while(!found && i < users.size()) {
            found = (users.get(i).getUserName().equals(userName) && users.get(i).getPassword().equals(password));
            i++;
        }

        if(found)
            user = users.get(i-1);
        return user;
    }


    @Override
    public boolean userNameTaken(String username) {
        //will get a list of all users in the database, then do a search for the user
        users = getUsers();
        int i = 0;
        boolean found = false;

        while(!found && i < users.size()) {
            found = users.get(i).getUserName().equals(username);
            i++;
        }
        return found;
    }
}
