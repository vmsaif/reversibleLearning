package data;

import java.util.List;

import objects.Flashcard;

public interface FlashcardPersistence {

    //getFlashcardSequential---returns the flashcards in the sequence stored in the table
    List<Flashcard> getFlashcardSequential();

    //insertFlashcard---inserts a flashcard in this database
    Flashcard insertFlashcard(Flashcard givenFlashcard);

    //deleteFlashcard---deletes a flashcard in this database
    void deleteFlashcard(Flashcard currentFlashcard);

}//FlashcardPersistence ends
