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
    }//constructor


    //connect to the local database
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown = true", "SA", "");
    }//connection


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
    public void insertFolder(String folder) {
        try(final Connection connection = connection()){
            final PreparedStatement fl = connection.prepareStatement("INSERT INTO folderTable VALUES(?)");
            fl.setString(1, folder);
            fl.executeUpdate();
            fl.close();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//insertFolder


    @Override
    public void insertCardToFolder(Flashcard flashcard, String folder){
        try(final Connection connection = connection()){
            final PreparedStatement uf = connection.prepareStatement("INSERT INTO card_folders VALUES(?,?)");
            uf.setString(1, flashcard.getQuestion());
            uf.setString(2, folder);
            uf.executeUpdate();
            uf.close();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//insertCardToFolder


    @Override
    public void deleteFolder(String folderName) {
        try(final Connection connection = connection()){
            final PreparedStatement fl = connection.prepareStatement("DELETE FROM folderTable WHERE folderName = ?");
            fl.setString(1, folderName);
            fl.executeUpdate();
            fl.close();

            final PreparedStatement ff = connection.prepareStatement("DELETE FROM card_folders WHERE folderName = ?");
            ff.setString(1, folderName);
            ff.executeUpdate();
            ff.close();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//deleteFolder


    @Override
    public List<Flashcard> getFolderCards(String folderName) {
        List<Flashcard> fcardList = new ArrayList<>();
        try(final Connection connection = connection()){
            final PreparedStatement fol = connection.prepareStatement("SELECT * FROM"
                    + " flashcardTable INNER JOIN card_folders"
                    + " ON card_folders.question = flashcardTable.question"
                    + " WHERE card_folders.folderName = ?");
            fol.setString(1, folderName);
            final ResultSet rs = fol.executeQuery();
            while(rs.next()){
                fcardList.add(fromResultSet(rs)); //adding each flashcard in the table to our list
            }//while
            rs.close();
            fol.close();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
        return fcardList;
    }//getFolderCards


    @Override
    public void removeCardFromFolder(Flashcard flashcard, String folder) {
        try(final Connection connection = connection()){
            final PreparedStatement fol = connection.prepareStatement("DELETE FROM card_folders"
                    + " WHERE card_folders.folderName = ?"
                    + " AND card_folders.question = ?");
            fol.setString(1, folder);
            fol.setString(2, flashcard.getQuestion());
            fol.executeUpdate();
            fol.close();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//removeCardFromFolder


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
        return folders;
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
            fl.close();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
    }//deleteFlashCard

    @Override
    public Flashcard getFlashcard(String question) {
        Flashcard flashcard = null;
        try(final Connection connection = connection()){
            final PreparedStatement fl = connection.prepareStatement("SELECT * FROM flashcardTable WHERE question = ?");
            fl.setString(1, question);
            final ResultSet rs = fl.executeQuery();
            while(rs.next())
                flashcard = fromResultSet(rs);

            rs.close();
            fl.close();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
        return flashcard;
    }//getFlashcard


    //getUserCards---select all the cards associated with this user
    public List<Flashcard> getUserCards(String userName){
        List<Flashcard> usersFlashcards = new ArrayList<>();
        try(final Connection connection = connection()){
            final PreparedStatement uf = connection.prepareStatement("SELECT * FROM flashcardTable WHERE userName = ?");
            uf.setString(1, userName);
            final ResultSet rs = uf.executeQuery();
            while(rs.next()){
                usersFlashcards.add(fromResultSet(rs));
            }//while
            rs.close();
            uf.close();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
        return usersFlashcards;
    }//getUserCards


    @Override
    public List<Flashcard> getAllFlashcards() {
        List<Flashcard> usersFlashcards = new ArrayList<>();
        try(final Connection connection = connection()){
            final PreparedStatement uf = connection.prepareStatement("SELECT * FROM flashcardTable");
            final ResultSet rs = uf.executeQuery();
            while(rs.next()){
                usersFlashcards.add(fromResultSet(rs));
            }//while
            rs.close();
            uf.close();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
        return usersFlashcards;
    }//getAllFlashcards


    public List<String> getAllFolders() {
        List<String> folders = new ArrayList<>();
        try(final Connection connection = connection()){
            final PreparedStatement uf = connection.prepareStatement("SELECT * FROM folderTable");
            final ResultSet rs = uf.executeQuery();
            while(rs.next()){
                folders.add(rs.getString("folderName"));
            }//while
            rs.close();
            uf.close();
        }//try
        catch (final SQLException e) {
            e.printStackTrace(System.out);
        }//catch SQLException
        return folders;
    }//getAllFolders


}//FlashcardPersistenceHSQLDB