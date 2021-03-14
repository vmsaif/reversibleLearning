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
    //private Connection connection;
    private final String dbPath;


    //constructor
    public FlashcardPersistenceHSQLDB(final String dbPath){
        this.dbPath = dbPath;
//        try{
//            Class.forName("org.hsqldb.jdbcDriver");
//            // creates an in-memory database
//            connection = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "");
//            createFlashcardTable(); //creating a table
//        }//try
//        catch (ClassNotFoundException e) {
//            e.printStackTrace(System.out);
//        }//catch ClassNotFountException
//        catch (SQLException e) {
//            e.printStackTrace(System.out);
//        }//catch SQLException
    }//constructor


    //connect to the local database
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown = true", "SA", "");
    }//connection


//    //createFlashcardTable
//    private void createFlashcardTable(){
//        String flashcardTable = "CREATE TABLE flashcardTable ("
//                + " question VARCHAR(100) NOT NULL,"
//                + " answer VARCHAR(100)"
//                + " userName VARCHAR(20),"
//                + " PRIMARY KEY(question));";
//        /*This table will have 3 columns which contains the information regarding this flashcard
//        * question will contain the question on the Flashcard. Since a question is the most unique thing on a flashcard it is the primary key
//        * answer will contain the answer of the question on the Flashcard
//        * userName will contain a string with the name of the user or guest if app is used as a guest*/
//        try{
//            connection.createStatement().executeUpdate(flashcardTable);
//
//            String folderTable = "CREATE TABLE folderTable ("
//                    + " folderName VARCHAR(20) NOT NULL,"
//                    + " PRIMARY KEY(folderName));";
//
//            connection.createStatement().executeUpdate(folderTable);
//
//            String card_folders = "CREATE TABLE card_folders ("
//                    + " question VARCHAR(100) NOT NULL,"
//                    + " folderName VARCHAR(20) NOT NULL,"
//                    + " PRIMARY KEY(question, folderName));";
//        }//try
//        catch (SQLException e) {
//            e.printStackTrace(System.out);
//        }//catch SQLException
//    }//createFlashcardTable


    @Override
    public void insertFlashcard(Flashcard givenFlashcard){
        try(final Connection connection = connection()){
            final PreparedStatement ft = connection.prepareStatement("INSERT INTO flashcardTable VALUES(?,?,?)");
            //grabbing the data from the givenFlashcard to store it into our table
            ft.setString(1, givenFlashcard.getQuestion());
            ft.setString(2, givenFlashcard.getAnswer());
            ft.setString(3, givenFlashcard.getUserName());
            //ft.setString(4, givenFlashcard.getFolderNames());
            ft.executeUpdate();
            ft.close();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//insertFlashcard


    @Override
    public void insertFolder(Flashcard flashcard, String folder) {
        try(final Connection connection = connection()){
            final PreparedStatement fl = connection.prepareStatement("INSERT INTO folderTable VALUES(?)");
            fl.setString(1, folder);
            fl.executeUpdate();
            fl.close();

            PreparedStatement uf = connection.prepareStatement("INSERT INTO card_folders VALUES(?,?)");
            uf.setString(1, flashcard.getQuestion());
            uf.setString(2, folder);
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//insertFolder

    @Override
    public List<String> getFlashcardFolders(Flashcard flashcard) {
        List<String> folders = new ArrayList<>();
        try(final Connection connection = connection()){
            final Statement fol = connection.createStatement();
            ResultSet rs = fol.executeQuery("SELECT card_folders.* FROM"
                    + " flashcardTable INNER JOIN card_folders"
                    + " ON card_folders.question = flashcardTable.question"
                    + " INNER JOIN folderTable"
                    + " ON folderTable.folderName = card_folders.folderName");
            while(rs.next()){
                String folderName = rs.getString("folder_name");
                folders.add(folderName);
            }//while
            rs.close();
            fol.close();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
        return null;
    }//getFlashcardFolders


    @Override
    public List<Flashcard> getFlashcardSequential(){
        List<Flashcard> flashcards = new ArrayList<>();
        try(final Connection connection = connection()){
            final Statement fc = connection.createStatement();
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

        return new Flashcard(question, answer, userName);
    }//fromResultSet


    @Override
    public void deleteFlashcard(Flashcard currentFlashcard) {
        try(final Connection connection = connection()){
            final PreparedStatement fl = connection.prepareStatement("DELETE FROM flashcardTable WHERE question = ?");
            fl.setString(1, currentFlashcard.getQuestion());
            fl.executeUpdate();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//deleteFlashCard


}//FlashcardPersistenceHSQLDB

/*add a section of adding folders and users to guests*/
