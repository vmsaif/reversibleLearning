package interfaces;

import objects.User;

public interface IUserDB {

    //Passed: a user
    //add the passed user to the database
    public void addUser(User user);

    //returns a boolean value if the passes user was found in the database
    public boolean userExists(User user);
}
