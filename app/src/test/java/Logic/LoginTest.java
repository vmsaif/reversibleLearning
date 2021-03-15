package Logic;

import org.junit.Before;
import org.junit.Test;

import logic.Account;
import objects.User;

import static org.junit.Assert.*;

public class LoginTest {

    private User user;
    private Account account;

    @Before
    public void setFakeUser(){
        user = new User("Fake@gmail.com", "Fake");
        account = new Account();
        account.addNewAccount(user.getUserName(), user.getPassword());
    }

    @Test
    public void testUserExist(){
        assertTrue(account.login("Fake@gmail.com", "Fake"));
    }
}
