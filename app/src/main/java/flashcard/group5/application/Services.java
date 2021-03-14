package flashcard.group5.application;

import android.util.Log;

import data.FlashcardPersistence;
import data.hsqldb.FlashcardPersistenceHSQLDB;
import data.UserPersistence;
import data.hsqldb.UserPersistenceHSQLDB;

public class Services {
    private static FlashcardPersistence flashcardPersistence = null;
    private static UserPersistence userPersistence = null;
    private static String dbName = "DB";  //database file name

    //getFlashcardPersistence---creates a new data layer for Flashcards
    public static synchronized FlashcardPersistence getFlashcardPersistence(){
        if(flashcardPersistence == null){
            flashcardPersistence = new FlashcardPersistenceHSQLDB();
        }//if
        return flashcardPersistence;
    }//getFlashcardPersistence

    //getUserPersistence
    public static synchronized UserPersistence getUserPersistence()
    {
        if (userPersistence == null)
        {
            userPersistence = new UserPersistenceHSQLDB(getDBPathName());
        }
        return userPersistence;
    }

    public static void setDBPathName(final String name) {

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbName = name;
    }

    public static String getDBPathName()
    {
        return dbName;
    }


}//Services class
