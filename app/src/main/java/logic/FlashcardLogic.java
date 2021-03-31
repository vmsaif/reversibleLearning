package logic;

import java.util.Collections;
import java.util.List;

import data.FlashcardPersistence;
import flashcard.group5.application.Services;
import interfaces.IFlashcardLogic;
import objects.Flashcard;

public class FlashcardLogic implements IFlashcardLogic {

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
    }//insertFlashcard


    //deleteFlashcard
    public void deleteFlashcard(Flashcard currentCard){
        fCardPersistence.deleteFlashcard(currentCard);
    }//deleteFlashcard


    //getFlashcard---returns flashcard given a question
    public Flashcard getFlashcard(String question){
        return fCardPersistence.getFlashcard(question);
    }//getFlashcard


    //getUsersCards---given a user name it will return all the cards created by this user
    public List<Flashcard> getUsersCards(String userName){
        return fCardPersistence.getUserCards(userName);
    }//getUsersCards


    //getAllCards---returns all the cards in our database
    public List<Flashcard> getAllCards(){
        return fCardPersistence.getAllFlashcards();
    }//getAllCards

    @Override
    public List<String> getAllFolders() {
        return fCardPersistence.getAllFolders();
    }//getAllFolders


    @Override
    public void insertCardToFolder(Flashcard flashcard, String folder) {
        fCardPersistence.insertCardToFolder(flashcard,folder);
    }//insertCardToFolder


    @Override
    public void insertFolder(String folder){
        fCardPersistence.insertFolder(folder);
    }//insertFolder


    @Override
    public void deleteFolder(String folderName) {
        fCardPersistence.deleteFolder(folderName);
    }//deleteFolder


    @Override
    public List<Flashcard> getFolderCards(String folderName) {
        return fCardPersistence.getFolderCards(folderName);
    }//getFolderCards

    @Override
    public void removeCardFromFolder(Flashcard flashcard, String folder) {
        fCardPersistence.removeCardFromFolder(flashcard, folder);
    }//removeCardFromFolder


}//FlashcardLogic class
