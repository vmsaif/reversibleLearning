package data.stubs;

import java.util.ArrayList;
import java.util.List;


import data.FlashcardPersistence;
import objects.Flashcard;

public class FlashcardPersistenceStub implements FlashcardPersistence {

    // List of Flashcards
    private List<Flashcard> flashcards;


    //constructor
    public FlashcardPersistenceStub(){
        this.flashcards = new ArrayList<>();
        flashcards.add(new Flashcard("What is the answer of this question?", "answer1000", "guest3000"));
        flashcards.add(new Flashcard("This is a question", "This is an answer", "mike"));
        flashcards.add(new Flashcard("question", "answer", "group5"));
        Flashcard fCard = new Flashcard("question1", "answer1", "user1");
        fCard.addFolderName("folder1");
        fCard.addFolderName("folder2");
        fCard.addFolderName("folder13");
        flashcards.add(fCard);
    }//constructor


    @Override
    public void insertFlashcard(Flashcard givenFlashcard){
        flashcards.add(givenFlashcard);
    }//insertFlashcard


    @Override
    public void insertFolder(Flashcard flashcard, String folder) {
        flashcard.addFolderName(folder);
    }//insertFolder

    @Override
    public List<String> getFlashcardFolders(Flashcard flashcard) {
        return flashcard.getFolderNames();
    }//getFlashcardFolders


    @Override
    public List<Flashcard> getFlashcardSequential(){
        return flashcards;
    }//getFlashcardSequential


    @Override
    public void deleteFlashcard(Flashcard currentFlashcard) {
        flashcards.remove(currentFlashcard);
    }//deleteFlashCard


}
