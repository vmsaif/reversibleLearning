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

}//IFlashcardLogic
