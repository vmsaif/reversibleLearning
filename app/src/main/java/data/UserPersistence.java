package data;

import java.util.List;

import objects.User;

public interface UserPersistence {
    //getUserSequential---returns the users in the sequence stored in the table
    List<User> getUserSequential();

    //getUser---return a user in the database, if not exists, return null.
    //It is mostly for looking up whether the user exists in the database
    User getUser(String userName, String password);

    //insertUser---inserts a user in this database
    void insertUser(User givenUser);

    //deleteUser---deletes a user in this database
    void deleteUser(User givenUser);

    //modifyUserName---modifies a user's username in this database
    void modifyUser(User givenUser, User newUser);

}//UserPersistence ends

