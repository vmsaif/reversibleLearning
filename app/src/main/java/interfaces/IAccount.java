package interfaces;

public interface IAccount {

    //Attempt to add give user credentials to the database.
    //return a boolean if success or fail (if the username already exists in the database)
    boolean addNewAccount(String userName, String password);
}
