package logic;

import java.util.Collections;
import java.util.List;

import data.FlashcardPersistence;
import flashcard.group5.application.Services;
import objects.Flashcard;

public class FlashcardLogic{

    //variables
    private Flashcard fCard;
    private FlashcardPersistence fCardPersistence;
    private List<Flashcard> flashcards;
    private int currentCard;


    //constructor1
    public FlashcardLogic(){
        fCardPersistence = Services.getFlashcardPersistence();
        fCard = null;
        flashcards = null;
        currentCard = 0;
    }//constructor1


    //constructor2---if we want to give our own database
    public FlashcardLogic(FlashcardPersistence flashcardPersistence){
        this();
        this.fCardPersistence = flashcardPersistence;
    }//constructor2


    //getFlashcards---returns a list of flashcards
    public List<Flashcard> getFlashcards(){
        flashcards = fCardPersistence.getFlashcardSequential();
        return Collections.unmodifiableList(flashcards);
    }//getFlashcards


    //getSequential
    public Flashcard getSequential(){
        if(flashcards == null){
            flashcards = fCardPersistence.getFlashcardSequential();
            currentCard = 0;
        }//if
        if(currentCard < flashcards.size()){
            fCard = flashcards.get(currentCard);
            currentCard++;
        }//if
        else{
            flashcards = null;
            fCard = null;
            currentCard = 0;
        }//else
        return fCard;
    }//getSequential


    //insertFlashcard
    public void insertFlashcard(Flashcard flashCard){
        fCardPersistence.insertFlashcard(flashCard);
        currentCard++;
    }//insertFlashcard


    //deleteFlashcard
    public void deleteFlashcard(Flashcard currentCard){
        fCardPersistence.deleteFlashcard(currentCard);
    }//deleteFlashcard


}//FlashcardLogic class
