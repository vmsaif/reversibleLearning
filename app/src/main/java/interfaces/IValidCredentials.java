package interfaces;

import objects.User;

public interface IValidCredentials {

    //verifies and return boolean value if the user exists in the database
    public boolean userExists();

}
