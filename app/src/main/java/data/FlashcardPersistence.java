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
    void insertFolder(String folder);

    //getFlashcardFolders---returns all the folders that contains this flashcard
    List<String> getFlashcardFolders(Flashcard flashcard);

    //getUserCards---select all the cards associated with this user
    List<Flashcard> getUserCards(String userName);

    //getAllFlashcards---returns all the flashcards in our database
    List<Flashcard> getAllFlashcards();

    //getAllFolders---returns all the folders ever created
    List<String> getAllFolders();

    //insertCardToFolder---whenever we add a card to a folder we need to add a column in our database indicating this
    void insertCardToFolder(Flashcard flashcard, String folder);

    //deleteFolder---deletes a folder
    void deleteFolder(String folderName);

    //getFolderCards---returns all the cards that this folder contains
    List<Flashcard> getFolderCards(String folderName);

    //removeCardFromFolder---removes a card from the folder
    void removeCardFromFolder(Flashcard flashcard, String folder);

}//FlashcardPersistence ends
