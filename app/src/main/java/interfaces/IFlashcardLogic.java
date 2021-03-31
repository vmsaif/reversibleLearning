package interfaces;

import java.util.Collections;
import java.util.List;

import objects.Flashcard;

public interface IFlashcardLogic {

    //getFlashcards---returns a list of flashcards
    List<Flashcard> getFlashcards();


    //getSequential---returns flashcards one by one as stored in the database
    Flashcard getSequential();


    //insertFlashcard
    void insertFlashcard(Flashcard flashCard);


    //deleteFlashcard
    void deleteFlashcard(Flashcard currentCard);


    //getFlashcard---returns flashcard given a question
    Flashcard getFlashcard(String question);

    //getUsersCards---given a user name it will return all the cards created by this user
    List<Flashcard> getUsersCards(String userName);

    //getAllCards---returns all the cards in our database
    List<Flashcard> getAllCards();

    //getAllFolders---returns all the folders in our database
    List<String> getAllFolders();

    //insertCardToFolder---helps pass information to the database to add an entry into card_folders table
    void insertCardToFolder(Flashcard flashcard, String folder);

    //insertFolder---inserts a new folder in our database along with the flashcard it is associated with
    void insertFolder(String folder);

    //deleteFolder---deletes a folder
    void deleteFolder(String folderName);

    //getFolderCards---returns all the cards that this folder contains
    List<Flashcard> getFolderCards(String folderName);

    //removeCardFromFolder---removes a card from the folder
    void removeCardFromFolder(Flashcard flashcard, String folder);



}//IFlashcardLogic
