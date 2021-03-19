package data;

import java.util.List;

import objects.Flashcard;

public interface FlashcardPersistence {

    //getFlashcardSequential---returns the flashcards in the sequence stored in the table
    List<Flashcard> getFlashcardSequential();

    //insertFlashcard---inserts a flashcard in this database
    void insertFlashcard(Flashcard givenFlashcard);

    //deleteFlashcard---deletes a flashcard in this database
    void deleteFlashcard(Flashcard currentFlashcard);


    //getFlashcard---getting a flashcard according to the question
    Flashcard getFlashcard(String question);

    //insertFolder---inserts a folder associated with the flashCard
    void insertFolder(Flashcard flashcard, String folder);

    //getFlashcardFolders---returns all the folders that contains this flashcard
    List<String> getFlashcardFolders(Flashcard flashcard);

    //getUserCards---select all the cards associated with this user
    List<Flashcard> getUserCards(String userName);

    //getAllFlashcards---returns all the flashcards in our database
    List<Flashcard> getAllFlashcards();




}//FlashcardPersistence ends
