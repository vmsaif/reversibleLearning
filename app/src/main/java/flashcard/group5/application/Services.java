package flashcard.group5.application;

import data.FlashcardPersistence;
import data.hsqldb.FlashcardPersistenceHSQLDB;

public class Services {
    private static FlashcardPersistence flashcardPersistence = null;


    //getFlashcardPersistence---creates a new data layer for Flashcards
    public static synchronized FlashcardPersistence getFlashcardPersistence(){
        if(flashcardPersistence == null){
            flashcardPersistence = new FlashcardPersistenceHSQLDB();
        }//if
        return flashcardPersistence;
    }//getFlashcardPersistence
}//Services class
