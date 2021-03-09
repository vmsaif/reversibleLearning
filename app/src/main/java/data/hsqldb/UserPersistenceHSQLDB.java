package data.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import data.UserPersistence;
import objects.User;

public class  UserPersistenceHSQLDB implements UserPersistence {

    //variables
    private Connection connection;


    //constructor
    public UserPersistenceHSQLDB(){
        try{
            Class.forName("org.hsqldb.jdbcDriver");
            // creates an in-memory database
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "");
            createUsersTable(); //creating a table
        }//try
        catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }//catch ClassNotFountException
        catch (SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//constructor


    //createUsersTable
    private void createUsersTable(){
        String usersTable = "create table usersTable ("
                + " userName VARCHAR(100),"
                + " password VARCHAR(100)"
                + " primary key(userName, password);";
        /*This table will have 2 columns which contains the information regarding this user
         * userName will contain the user name for the profile.
         * password will contain the user's password to login
         * The combination of userName and password will form the primary key to identify our users*/
        try{
            connection.createStatement().executeUpdate(usersTable);
        }//try
        catch (SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//createUsersTable


    //insertUser
    @Override
    public void insertUser(User givenUser){
        try{
            PreparedStatement ft = connection.prepareStatement("INSERT INTO usersTable VALUES(?,?)");
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
        try{
            Statement fc = connection.createStatement();
            ResultSet rs = fc.executeQuery("SELECT * FROM usersTable");
            while(rs.next()){
                User user = fromResultSet(rs);
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


    //fromResultSet---helper method to access the columns of users table and returning a user object
    private User fromResultSet(ResultSet rs) throws SQLException{
        String userName = rs.getString("userName");
        String password = rs.getString("password");

        User user = new User(userName, password);
        return user;
    }//fromResultSet


    //deleteUser
    @Override
    public void deleteUser(User currentUser) {
        try{
            PreparedStatement fl = connection.prepareStatement("DELETE FROM usersTable WHERE userName = ?");
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
        try {
            PreparedStatement f1 = connection.prepareStatement("UPDATE usersTable SET userName = ? WHERE userName = ?");
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
        try {
            PreparedStatement f1 = connection.prepareStatement("UPDATE usersTable SET password = ? WHERE userName = ?");
            f1.setString(1, newPassword);
            f1.setString(2, givenUser.getUserName());
            f1.executeUpdate();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//modifyUserPassword


}//UserPersistenceHSQLDB


