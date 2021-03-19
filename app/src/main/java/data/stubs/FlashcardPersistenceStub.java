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
        int search = searchFlashcard(flashcard);
        if(search != -1){
            Flashcard temp = flashcards.get(search);
            temp.addFolderName(folder);
            flashcards.set(search, temp);
        }
    }//insertFolder

    @Override
    public List<String> getFlashcardFolders(Flashcard flashcard) {
        Flashcard card = null;
        int search = searchFlashcard(flashcard);
        if(search != -1){
            card = getFlashcardSequential().get(search);
        }
        return card == null ? null : card.getFolderNames();
    }//getFlashcardFolders


    @Override
    public List<Flashcard> getUserCards(String userName) {
        ArrayList<Flashcard> fCard = new ArrayList<>();
        for(int i=0; i<flashcards.size(); i++){
            if(flashcards.get(i).getUserName().equals(userName)){
                fCard.add(flashcards.get(i));
            }//if
        }//for i
        return fCard;
    }

    @Override
    public List<Flashcard> getAllFlashcards() {
        return flashcards;
    }//getAllFlashcards


    private int searchFlashcard(Flashcard flashcard){
        int result = -1;
        for(int i = 0; i < flashcards.size(); i++){
            if(flashcards.get(i).getQuestion().equals(flashcard.getQuestion()) &&
                    flashcards.get(i).getAnswer().equals(flashcard.getAnswer()) &&
                    flashcards.get(i).getUserName().equals(flashcard.getUserName())){
                result = i;
            }
        }
        return result;
    }


    @Override
    public List<Flashcard> getFlashcardSequential(){
        return flashcards;
    }//getFlashcardSequential


    @Override
    public void deleteFlashcard(Flashcard currentFlashcard) {
        flashcards.remove(currentFlashcard);
    }//deleteFlashCard

    @Override
    public Flashcard getFlashcard(String question) {
        Flashcard fCard = null;
        for(int i=0; i<flashcards.size(); i++){
            if(flashcards.get(i).getQuestion().equals(question)){
                fCard = flashcards.get(i);
            }//if
        }//for i
        return fCard;
    }//getFlashCard


}
