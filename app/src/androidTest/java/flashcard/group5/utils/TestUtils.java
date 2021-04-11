package flashcard.group5.utils;

import java.util.List;

import interfaces.IAccount;
import interfaces.IFlashcardLogic;
import logic.Account;
import logic.FlashcardLogic;
import objects.Flashcard;
import objects.User;

public class TestUtils {

    private IFlashcardLogic flashcardLogic;
    private IAccount account;

    public TestUtils(){
        flashcardLogic = new FlashcardLogic();
        account = new Account();
    }

    public void insertFlashcard(Flashcard card){
        flashcardLogic.insertFlashcard(card);
    }


    public void deleteFlashcard(Flashcard currentCard){
        flashcardLogic.deleteFlashcard(currentCard);
    }


    public Flashcard getFlashcard(String question){
        return flashcardLogic.getFlashcard(question);
    }


    public List<Flashcard> getUsersCards(String userName){
        return flashcardLogic.getUsersCards(userName);
    }


    public List<Flashcard> getAllCards(){
        return flashcardLogic.getAllCards();
    }


    public List<Flashcard> getFlashcards(){
        return flashcardLogic.getFlashcards();
    }


    public Flashcard getSequential(){
        return flashcardLogic.getSequential();
    }

    public boolean addNewAccount(String userName, String password) {
        return account.addNewAccount(userName, password);
    }

    public boolean login(String userName, String password){
        return account.login(userName, password);
    }

    public boolean changeUser(User userNew) {
        return account.changeUser(userNew);
    }

    public void deleteUser(){
        account.deleteUser();
    }

}
