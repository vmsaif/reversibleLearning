package data;
import org.junit.Before;
import org.junit.Test;

import objects.CardSide;
import objects.User;

import static org.junit.Assert.*;
public class UserDBTest {

    private UserDB users;

    @Before
    public void setUp(){
        users = new UserDB();
    }

    @Test
    public void TestAddUserAndUserExits(){
        User user = new User("user", "pass");
        // check the non-existent user from the userDB
        assertFalse("User doesn't exists in the UserDB", users.userExists(user));
        // add the user to the UserDB
        users.addUser(user);
        // check the existing user from the userDB
        assertTrue("User exists in the UserDB", users.userExists(user));
    }

}
