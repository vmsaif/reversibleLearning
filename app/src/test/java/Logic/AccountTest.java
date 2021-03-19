package Logic;

import org.junit.Before;
import org.junit.Test;

import data.stubs.UserPersistenceStub;
import logic.Account;
import objects.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AccountTest {

    private Account account;

    @Before
    public void setUp(){
        account = new Account(new UserPersistenceStub());
    }

    @Test
    public void TestAddAccount(){
        // test add a new account
        assertTrue("account added successfully", account.addNewAccount("group101","123"));
        assertTrue("account added successfully", account.addNewAccount("group102","password2"));
        // test add a duplicate account
        assertFalse("account not added successfully", account.addNewAccount("group101","123"));
        // add account with same username but different password
        assertFalse("account not added successfully", account.addNewAccount("group101","12345"));
        // add account with different username but same password
        assertTrue("account added successfully", account.addNewAccount("accounts23","123"));
        // add account with empty string as username and password
        assertTrue("account added successfully", account.addNewAccount("",""));
        // add account with empty spaces as username and a password
        assertTrue("account added successfully", account.addNewAccount("    ","     "));
    }


    @Test
    public void TestLoginAndGetLoggedUser(){
        // login when an account has a wrong password
        assertFalse("login unsuccessful", account.login("User","Password"));
        assertNull("no user is logged in", account.getLoggedUser());
        // login when an account has password with different case (lower/upper)
        assertFalse("login unsuccessful", account.login("USERNAME","PASSWORD"));
        assertNull("no user is logged in", account.getLoggedUser());
        // check if login succeeds
        assertTrue("Login successfully", account.login("username","password"));
        assertEquals("username is the same", account.getLoggedUser().getUserName(), "username");
        assertEquals("password is the same", account.getLoggedUser().getPassword(), "password");
        assertTrue("Login successfully", account.login("User","Pass"));
        assertEquals("username is the same", account.getLoggedUser().getUserName(), "User");
        assertEquals("password is the same", account.getLoggedUser().getPassword(), "Pass");
        // login when an account doesn't exist
        assertFalse("login unsuccessful", account.login("empty","false"));
    }
    @Test
    public void TestChangeUserName(){
        // change user when user currently is not logged in
        assertNull("no user", account.getLoggedUser());
        assertFalse("cannot change username", account.changeUser(new User("username", "test")));
        // change user when user currently is logged in
        assertTrue("Login successfully", account.login("username","password"));
        assertTrue("change successful", account.changeUser(new User("Name1", "password")));
        // login with old username
        assertFalse("Login unsuccessfully", account.login("username","password"));
        // login with new username
        assertTrue("Login successfully", account.login("Name1","password"));
        // change username with existing username
        assertFalse("cannot change username", account.changeUser(new User("User", "test")));
        // change the username with empty string
        assertTrue("change successful", account.changeUser(new User("", "")));
    }

    @Test
    public void TestChangeUserNameAndPassword(){
        // change name when user currently is not logged in
        assertNull("no user", account.getLoggedUser());
        assertFalse("cannot change username", account.changeUser(new User("username", "test")));
        // change username when user currently is logged in
        assertTrue("Login successfully", account.login("username","password"));
        assertTrue("change successful", account.changeUser(new User("Name1", "pass1")));
        // login with old username
        assertFalse("Login unsuccessfully", account.login("username","password"));
        // login with new username
        assertTrue("Login successfully", account.login("Name1","pass1"));
        // change username with existing username
        assertFalse("cannot change username", account.changeUser(new User("User", "test")));
        // change the username with empty string
        assertTrue("change successful", account.changeUser(new User("", "")));
    }


    @Test
    public void TestLogout(){
        // login the user
        assertTrue("Login successfully", account.login("username","password"));
        assertEquals("username is the same", account.getLoggedUser().getUserName(), "username");
        assertEquals("password is the same", account.getLoggedUser().getPassword(), "password");
        // logout user
        account.logout();
        assertNull("user has logged out", account.getLoggedUser());
    }
}
