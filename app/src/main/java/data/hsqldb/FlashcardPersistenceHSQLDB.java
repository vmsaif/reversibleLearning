package data.hsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.FlashcardPersistence;
import objects.Flashcard;

public class FlashcardPersistenceHSQLDB implements FlashcardPersistence {

    //variables
    private Connection connection;


    //constructor
    public FlashcardPersistenceHSQLDB(){
        try{
            Class.forName("org.hsqldb.jdbcDriver");
            // creates an in-memory database
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "");
            createFlashcardTable(); //creating a table
        }//try
        catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }//catch ClassNotFountException
        catch (SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//constructor


    //createFlashcardTable
    private void createFlashcardTable(){
        String flashcardTable = "create table flashcardTable ("
                + " question VARCHAR(100),"
                + " answer VARCHAR(100)"
                + " userName VARCHAR(20),"
                + " folder VARCHAR(20),"
                + " primary key(user,question);";
        /*This table will have 4 columns which contains the information regarding this flashcard
        * question will contain the question on the Flashcard. Since a question is the most unique thing on a flashcard it is the primary key
        * answer will contain the answer of the question on the Flashcard
        * userAccount will contain a string with the name of the user or guest if app is used as a guest
        * folder will contain a string associating this Flashcard with any folder, can be an empty string if not associated with any folder*/
        try{
            connection.createStatement().executeUpdate(flashcardTable);
        }//try
        catch (SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//createFlashcardTable


    @Override
    public Flashcard insertFlashcard(Flashcard givenFlashcard){
        try{
            PreparedStatement ft = connection.prepareStatement("INSERT INTO flashcardTable VALUES(?,?,?,?)");
            //grabbing the data from the givenFlashcard to store it into our table
            ft.setString(1, givenFlashcard.getQuestion());
            ft.setString(2, givenFlashcard.getAnswer());
            ft.setString(3, givenFlashcard.getUserName());
            ft.setString(4, givenFlashcard.getFolderName());
            ft.executeUpdate();
            ft.close();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
        return givenFlashcard;
    }//insertFlashcard


    @Override
    public List<Flashcard> getFlashcardSequential(){
        List<Flashcard> flashcards = new ArrayList<>();
        try{
            Statement fc = connection.createStatement();
            ResultSet rs = fc.executeQuery("SELECT * FROM flashcardTable");
            while(rs.next()){
                Flashcard fCard = fromResultSet(rs);
                flashcards.add(fCard); //adding each flashcard in the table to our list
            }//while
            rs.close();
            fc.close();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
        return flashcards;
    }//getFlashcardSequential


    //fromResultSet---helper method to access the columns of flashCard table and returning a flashcard object
    private Flashcard fromResultSet(ResultSet rs) throws SQLException{
        String question = rs.getString("question"); //retrieving data from the flashCard table
        String answer = rs.getString("answer");
        String userName = rs.getString("userName");
        String folderName = rs.getString("folder");

        Flashcard fCard = new Flashcard(question, answer, userName);
        fCard.setFolderName(folderName);
        return fCard;
    }//fromResultSet


    @Override
    public void deleteFlashcard(Flashcard currentFlashcard) {
        try{
            PreparedStatement fl = connection.prepareStatement("DELETE FROM flashcardTable WHERE question = ?");
            fl.setString(1, currentFlashcard.getQuestion());
            fl.executeUpdate();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//deleteFlashCard


}//FlashcardPersistenceHSQLDB

/*add a section of adding folders and users to guests*/
