package Logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import flashcard.group5.application.Services;
import logic.Account;
import objects.User;
import utils.TestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class AccountIT {
    private File tempDB;
    private Account account;
    private User user1;

    @Before
    public void setUp() throws Exception{
        tempDB = TestUtils.copyDB();
        account = new Account();
        user1 = new User("liza@test.com", "TEST");
        assertNotNull(account);
    }

    @Test
    public void TestEverything(){
        // add unique new account
        assertTrue("account successfully added", account.addNewAccount("account","password"));
        assertNull("no user is logged in", account.getLoggedUser());
        // check if login succeeds
        assertTrue("Login is successful", account.login(user1.getUserName(),user1.getPassword()));
        assertEquals("Users is the same", account.getLoggedUser().getUserName(), user1.getUserName());
        assertEquals("Users is the same", account.getLoggedUser().getPassword(), user1.getPassword());
        // change user
        assertTrue(account.changeUser(new User("Name1", "name1")));
        assertEquals("Users is the same", account.getLoggedUser().getUserName(), "Name1");
        assertTrue(account.changeUser(new User("UserName","pass1")));
        assertEquals("Users is the same", account.getLoggedUser().getPassword(), "pass1");
        // logout
        account.logout();
        assertNull("no user is logged in", account.getLoggedUser());
    }

    @After
    public void tearDown(){
        tempDB.deleteOnExit();
        Services.clean();
    }
}