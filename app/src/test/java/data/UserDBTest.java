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
        // add the user to the UserDB
        users.addUser(user);
        // remove the existing user from the userDB
        assertTrue("User successfully exits", users.userExits(user));
        assertFalse("User no longer exists in the UserDB", users.userExits(user));
    }

}
