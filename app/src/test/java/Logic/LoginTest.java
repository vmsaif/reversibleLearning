package Logic;

import org.junit.Before;
import org.junit.Test;

import logic.ValidCredentials;
import objects.User;

import static org.junit.Assert.*;

public class LoginTest {

    private User user;
    private ValidCredentials validCredentials;

    @Before
    public void setFakeUser(){
        user = new User("Fake@gmail.com", "Fake");
        validCredentials = new ValidCredentials(user);
    }

    @Test
    public void testUserExist(){
        assertFalse(validCredentials.userExists());
    }
}
