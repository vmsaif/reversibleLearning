package data.hsqldb;

import android.util.Log;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.UserPersistence;
import objects.User;

public class  UserPersistenceHSQLDB implements UserPersistence {

    //our path name to the database
    private final String dbPath;

    //constructor
    public UserPersistenceHSQLDB(final String dbPath){
        this.dbPath = dbPath;
    }//constructor

    //connect to the local database
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown = true", "SA", "");
    }

    //fromResultSet---helper method to access the columns of users table and returning a user object
    private User fromResultSet(ResultSet rs) throws SQLException{
        String userName = rs.getString("userName");
        String password = rs.getString("password");

        User user = new User(userName, password);
        return user;
    }//fromResultSet


    //receive a username and return the user if exists in the DB, otherwise return null
    @Override
    public User getUser(String userName, String password) {
        User user = null;
        try(final Connection c = connection()) {
            final PreparedStatement un = c.prepareStatement("SELECT * FROM users WHERE username = ? and password = ?");
            un.setString(1, userName);
            un.setString(2, password);

            final ResultSet rs = un.executeQuery();

            while(rs.next())
                user = fromResultSet(rs);

            rs.close();
            un.close();
        } catch (final SQLException e) {
            //TO DO: possibly create an error handler
            e.printStackTrace(System.out);
        }
        return user;
    }


    //insertUser
    @Override
    public void insertUser(User givenUser){
        try(final Connection c = connection()){
            final PreparedStatement ft = c.prepareStatement("INSERT INTO users VALUES(?,?)");
            //grabbing the data from the user to store it into our table
            ft.setString(1, givenUser.getUserName());
            ft.setString(2, givenUser.getPassword());
            ft.executeUpdate();
            ft.close();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//insertUser


    //getUserSequential
    @Override
    public List<User> getUserSequential(){
        List<User> users = new ArrayList<>();
        try(final Connection c = connection()) {
            final Statement fc = c.createStatement();
            ResultSet rs = fc.executeQuery("SELECT * FROM users");
            while(rs.next()){
                final User user = fromResultSet(rs);
                users.add(user); //adding each user in the table to our list
            }//while
            rs.close();
            fc.close();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
        return users;
    }//getUserSequential


    //deleteUser
    @Override
    public void deleteUser(User currentUser) {
        try(final Connection c = connection()) {
            PreparedStatement fl = c.prepareStatement("DELETE FROM users WHERE userName = ?");
            fl.setString(1, currentUser.getUserName());
            fl.executeUpdate();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//deleteUser


    //modifyUserName
    @Override
    public void modifyUserName(User givenUser, String newUserName) {
        try(final Connection c = connection())  {
            PreparedStatement f1 = c.prepareStatement("UPDATE users SET userName = ? WHERE userName = ?");
            f1.setString(1, newUserName);
            f1.setString(2, givenUser.getUserName());
            f1.executeUpdate();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//modifyUserName


    //modifyUserPassword
    @Override
    public void modifyUserPassword(User givenUser, String newPassword) {
        try(final Connection c = connection())  {
            PreparedStatement f1 = c.prepareStatement("UPDATE users SET password = ? WHERE userName = ?");
            f1.setString(1, newPassword);
            f1.setString(2, givenUser.getUserName());
            f1.executeUpdate();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//modifyUserPassword


}//UserPersistenceHSQLDB


