package logic;

import android.util.Log;

import java.util.Collections;
import java.util.List;

import data.UserPersistence;
import flashcard.group5.application.Services;
import interfaces.IAccount;
import objects.User;

public class Account implements IAccount {
    UserPersistence userPersistence;
    private User user;  //will contain the current logged in user, if not logged in then null
    private List<User> users;  //list of users

    public Account() {
        userPersistence = Services.getUserPersistence();
        users = null;
        user = null;
    }

    public Account(final UserPersistence userPersistence){
        this();
        this.userPersistence = userPersistence;
    }

    private List<User> getUsers() {
        users = userPersistence.getUserSequential();
        return Collections.unmodifiableList(users);
    }

    public boolean addNewAccount(String userName, String password) {
       boolean result = !userNameTaken(userName);

        if(result) {
            userPersistence.insertUser(new User(userName, password));
        }
        return result;
    }

    private boolean userNameTaken(String username) {
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

    public boolean login(String userName, String password){
        boolean found = false;
        //will get a list of all users in the database, then do a search for the user
        users = getUsers();
        int i = 0; //iterator through users list

        while(!found && i < users.size()) {
            found = (users.get(i).getUserName().equals(userName) && users.get(i).getPassword().equals(password));
            i++;
        }

        if(found) {
            //update current user only if login was successful, otherwise remain null
            user = users.get(i - 1);
        }
        return found;
    }

    public boolean changeUserName(String userName) {
        //get to this point only when a user is logged in
        //Just in case, still check for a user
        boolean result = false;

        if(user != null && !userNameTaken(userName)){
            userPersistence.modifyUserName(user, userName);
            user.changeUserName(userName);
            result = true;
        }
        return result;
    }

    public boolean changePassword(String password) {
        //get to this point only when a user is logged in
        //Just in case, still check for a user
        boolean result = false;

        if(user != null){
            userPersistence.modifyUserPassword(user, password);
            user.changePassword(password);
            result = true;
        }
        return result;
    }

    public User getLoggedUser(){
        return user;
    }

    public void logout() {
        user = null;
    }
}
