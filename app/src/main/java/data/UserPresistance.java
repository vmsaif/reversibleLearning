package data;

import java.util.List;

import objects.User;

public interface UserPresistance {
    //getUserSequential---returns the users in the sequence stored in the table
    List<User> getUserSequential();

    //insertUser---inserts a user in this database
    void insertUser(User givenUser);

    //deleteUser---deletes a user in this database
    void deleteUser(User givenUser);

    //modifyUserName---modifies a user's username in this database
    void modifyUserName(User givenUser);

    //modifyUserPassword---modifies a user's password in this database
    void modifyUserPassword(User givenUser);

}//UserPresistance ends

