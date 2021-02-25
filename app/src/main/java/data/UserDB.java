package data;

import android.util.Log;

import java.util.ArrayList;

import objects.User;

public class UserDB {
    ArrayList<User> userDB;

    public UserDB() {
        this.userDB = new ArrayList<>();
    }

    public void addUser(User user){
        this.userDB.add(user); }

    public boolean userExits(User user){
        boolean found = false;
        for (int i = 0; i < userDB.size() && !found; i++)
            found = userDB.get(i).getUserName().equals(user.getUserName()) &&
                    userDB.get(i).getPassword().equals(user.getPassword());
        return found;
    }

    public void print(){
        for(int i = 0; i < userDB.size(); i++)
            Log.d("DEBBUG", "User DB item: " + userDB.get(i).getUserName());
    }
}