package data;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import data.stubs.UserPersistenceStub;
import objects.User;

import static org.junit.Assert.*;

public class UserPersistenceTest {

    private UserPersistence db;

    @Before
    public void setUp() {
        db = new UserPersistenceStub();
    }

    @Test
    public void TestGetUserSequential(){
        List<User> users = db.getUserSequential();
        // check the size
        assertEquals(users.size(), 4);
        // check the content
        assertEquals(users.get(0).getUserName(), "liza@test.com");
        assertEquals(users.get(0).getPassword(), "test123");
    }

    @Test
    public void TestGetUser(){
        User user = db.getUser("liza@test.com","test123");
        // check user exists
        assertNotNull(user);
        // check the content
        assertEquals(user.getUserName(), "liza@test.com");
        assertEquals(user.getPassword(), "test123");
        // check user that doesn't exists
        assertNull(db.getUser("notexists@test.com","notExists"));
    }

    @Test
    public void TestInsertUser(){
        // insert a user
        User user = new User("random@random.text", "passRandom");
        db.insertUser(user);
        // find the user to see if it is successfully inserted
        User foundUser = db.getUser("random@random.text", "passRandom");
        // check user exists
        assertNotNull(user);
        // check the content
        assertEquals(foundUser.getUserName(), user.getUserName());
        assertEquals(foundUser.getPassword(), user.getPassword());
        assertEquals(db.getUserSequential().size(), 5);
        // insert a duplicate
        db.insertUser(user);
        foundUser = db.getUser("random@random.text", "passRandom");
        // check the content
        assertEquals(foundUser.getUserName(), user.getUserName());
        assertEquals(foundUser.getPassword(), user.getPassword());
        assertEquals(db.getUserSequential().size(), 6);
    }

    @Test
    public void TestDeleteUser(){
        List<User> users = db.getUserSequential();
        // delete every user
        for(int i = 0; i < 4; i++) {
            db.deleteUser(users.get(0));
            // check the size
            assertEquals(db.getUserSequential().size(), 3-i);
        }
    }


    @Test
    public void TestModifyUserName(){
        // find a user
        User user = db.getUser("liza@test.com","test123");
        // modify the username
        db.modifyUserName(user,"group5@test.com");
        // find the old username
        user = db.getUser("liza@test.com","test123");
        assertNull(user);
        // find the new username
        user = db.getUser("group5@test.com","test123");
        assertNotNull(user);
        assertEquals(user.getUserName(), "group5@test.com");
        assertEquals(user.getPassword(), "test123");
    }

    @Test
    public void TestModifyUserPassword(){
        // find a user
        User user = db.getUser("liza@test.com","test123");
        // modify the password
        db.modifyUserPassword(user,"newPassword");
        // find the old password
        user = db.getUser("liza@test.com","test123");
        assertNull(user);
        // find the new password
        user = db.getUser("liza@test.com","newPassword");
        assertNotNull(user);
        assertEquals(user.getUserName(), "liza@test.com");
        assertEquals(user.getPassword(), "newPassword");
    }

}
