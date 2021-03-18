package data.stubs;

import java.util.ArrayList;
import java.util.List;

import data.UserPersistence;
import objects.User;

public class UserPersistenceStub implements UserPersistence {
    // List of User
    private List<User> users;

    //constructor
    public UserPersistenceStub(){
        this.users = new ArrayList<>();
        users.add(new User("liza@test.com", "test123"));
        users.add(new User("group5","password5"));
        users.add(new User("User","Pass"));
        users.add(new User("username","password"));
    }//constructor


    //receive a username and return the user if exists in the DB, otherwise return null
    @Override
    public User getUser(String userName, String password) {
        User user = null;
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUserName().equals(userName) && users.get(i).getPassword().equals(password)){
                user = users.get(i);
            }
        }
        return user;
    }


    //insertUser
    @Override
    public void insertUser(User givenUser){
        users.add(givenUser);
    }//insertUser


    //getUserSequential
    @Override
    public List<User> getUserSequential(){
        return users;
    }//getUserSequential


    //deleteUser
    @Override
    public void deleteUser(User currentUser) {
        users.remove(currentUser);
    }//deleteUser


    //modifyUserName
    @Override
    public void modifyUserName(User givenUser, String newUserName) {
        int index = users.indexOf(givenUser);
        User user = users.get(index);
        user.changeUserName(newUserName);
        users.set(index, user);
    }//modifyUserName


    //modifyUserPassword
    @Override
    public void modifyUserPassword(User givenUser, String newPassword) {
        int index = users.indexOf(givenUser);
        User user = users.get(index);
        user.changePassword(newPassword);
        users.set(index, user);
    }//modifyUserPassword
}
