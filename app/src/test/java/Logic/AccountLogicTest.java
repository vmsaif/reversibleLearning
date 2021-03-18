//package Logic;
//
//
//import org.junit.Before;
//import org.junit.Test;
//
//import logic.Account;
//import objects.User;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//public class AccountLogicTest {
//
//    private Account db;
//    private User user1;
//    private User user2;
//    private User user3;
//    private User user4;
//    private User emptyUP;
//    private User user1diffP;
//    private User spaceUser;
//
//
//    @Before
//    public void setUp(){
//        db = new Account();
//        user1 = new User("username1", "password1");
//        user2 = new User("username2", "PASSWORD2");
//        user3 = new User("username3", "password3");
//        user4 = new User("username4", "password1");
//        emptyUP = new User("", "");
//        spaceUser = new User("    ", "     ");
//    }
//
//    @Test
//    public void TestAddAccount(){
//        // add unique new accounts
//        assertTrue("account successfully added", db.addNewAccount(user1.getUserName(),user1.getPassword()));
//        assertTrue("account successfully added", db.addNewAccount(user2.getUserName(),user2.getPassword()));
//        assertTrue("account successfully added", db.addNewAccount(user3.getUserName(),user3.getPassword()));
//        // add duplicate account
//        assertFalse("account not added", db.addNewAccount(user1.getUserName(),user1.getPassword()));
//        // add account with same username but different password
//        assertFalse("account not added", db.addNewAccount(user2.getUserName(),"password2"));
//        // add account with different username but same password
//        assertTrue("account successfully added", db.addNewAccount(user4.getUserName(),user4.getPassword()));
//        // add account with empty string as username and password
//        assertTrue("account successfully added", db.addNewAccount(emptyUP.getUserName(),emptyUP.getPassword()));
//        // add account with empty string as username and a password
//        assertTrue("account successfully added", db.addNewAccount(emptyUP.getUserName(),"password"));
//        // add account with spaces  as username and password
//        assertTrue("account successfully added", db.addNewAccount(spaceUser.getUserName(),spaceUser.getPassword()));
//    }
//
//    @Test
//    public void TestLoginAndGetLoggedUser(){
//        // add new accounts
//        db.addNewAccount(user1.getUserName(),user1.getPassword());
//        db.addNewAccount(user2.getUserName(),user2.getPassword());
//        db.addNewAccount(user3.getUserName(),user3.getPassword());
//        // check if login succeeds
//        assertTrue("Login successfully", db.login(user1.getUserName(),user1.getPassword()));
//        assertEquals("user is the same", db.getLoggedUser(), user1);
//        assertTrue("Login successfully", db.login(user2.getUserName(),user2.getPassword()));
//        assertEquals("user is the same", db.getLoggedUser(), user2);
//        assertTrue("Login successfully", db.login(user3.getUserName(),user3.getPassword()));
//        assertEquals("user is the same", db.getLoggedUser(), user3);
//        // login when an account has a wrong password
//        assertFalse("login unsuccessful", db.login(user1.getUserName(), "password2"));
//        assertTrue("no user is logged in", db.getLoggedUser().equals(null));
//        // login when an account has password with different case
//        assertFalse("login unsuccessful", db.login(user2.getUserName(), "password2"));
//        assertFalse("Login unsuccessful", db.login(user3.getUserName(), "PASSWORD3"));
//        // login when an account doesn't exist
//        assertFalse("login unsuccessful", db.login(user4.getUserName(),user4.getPassword()));
//        assertTrue("no user is logged in", db.getLoggedUser().equals(null));
//    }
//
//    @Test
//    public void TestChangeUserName(){
//        // add new accounts
//        db.addNewAccount(user1.getUserName(), user1.getPassword());
//        db.addNewAccount(user2.getUserName(), user2.getPassword());
//        // change name when user currently is not logged in
//        assertFalse(db.changeUserName("Name1"));
//        // login the user
//        db.login(user1.getUserName(), user1.getPassword());
//        // change username when user currently is logged in
//        assertTrue(db.changeUserName("Name1"));
//        // login with old username
//        assertFalse(db.login(user1.getUserName(), user1.getPassword()));
//        // login with new username
//        assertTrue(db.login("name1", user1.getPassword()));
//        // change username with existing username
//        assertFalse(db.changeUserName(user2.getUserName()));
//        // change the username with empty string
//        assertFalse(db.changeUserName(emptyUP.getUserName()));
//    }
//
//    @Test
//    public void TestChangePassword(){
//        // add new accounts
//        db.addNewAccount("account1", "password1");
//        db.addNewAccount("account2", "password2");
//        // change password when user currently is not logged in
//        assertFalse(db.changePassword("pass1"));
//        // login the user
//        db.login(user1.getUserName(), user1.getPassword());
//        // change password when user currently is logged in
//        assertTrue(db.changePassword("pass1"));
//        // login with old password
//        assertFalse(db.login(user1.getUserName(), user1.getPassword()));
//        // login with new password
//        assertTrue(db.login(user1.getUserName(), "pass1"));
//        // change password to empty string
//        assertTrue(db.changePassword(""));
//    }
//
//    @Test
//    public void TestLogout(){
//        // add new accounts
//        db.addNewAccount("account1", "password1");
//        // login the user
//        db.login(user1.getUserName(), user1.getPassword());
//        assertEquals("user is the same", db.getLoggedUser(), user1);
//        // logout user
//        db.logout();
//        assertTrue("no user is logged in", db.getLoggedUser().equals(null));
//    }
//
//}
