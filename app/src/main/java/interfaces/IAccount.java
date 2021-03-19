package interfaces;

import objects.User;

public interface IAccount {

    //Attempt to add give user credentials to the database.
    //return a boolean if success or fail (if the username already exists in the database)
    boolean addNewAccount(String userName, String password);

    //attempt to login by passing the userName and the password.
    //Will return a boolean value to confirm if login was successful or not.
    //Login is unsuccessful when the given credentials are not in the database
    //If successful, a private variable user will be saved so we will know who is the logged in user
    boolean login(String userName, String password);

    //attempt to change the current username
    //will return a boolean for success or failure
    //Will fail if the new userName already taken
    boolean changeUser(User userNew);


    //Will return the current user that is logged in
    //If no user logged in, should return a null
    User getLoggedUser();

    //logout - will set the current user to null
    void logout();
}
