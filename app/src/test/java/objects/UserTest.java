package objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User user;
    private String actualUsername = "username";
    private String actualPassword = "pass123";

    @Before
    public  void setUp(){
        user = new User(actualUsername, actualPassword);
    }

    @Test
    public void TestChangeAndGetUserName() {
        // test get the username
        assertEquals("the username is the same", user.getUserName(), actualUsername);
        // test change the username
        String newUsername = "newName321";
        user.changeUserName(newUsername);
        assertEquals("the username is the same", user.getUserName(), newUsername);
    }

    @Test
    public void TestChangeAndGetPassword() {
        // test get the password
        assertEquals("the password is the same", user.getPassword(), actualPassword);
        // test change the password
        String newPassword = "newPassword3214";
        user.changePassword(newPassword);
        assertEquals("the password is the same", user.getPassword(), newPassword);
    }

}