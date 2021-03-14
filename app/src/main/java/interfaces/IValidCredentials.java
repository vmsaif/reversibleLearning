package interfaces;

import java.util.List;

import data.UserPersistence;
import objects.User;

public interface IValidCredentials {

    //return a list of users in the database
    List<User> getUsers();

    //return a user if in the database. Otherwise, return null.
    User getUser(String userName, String password);

    //return a boolean value if the username is taken - is the username is in the database
    boolean userNameTaken(String username);

}
