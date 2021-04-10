package Logic;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import data.stubs.FlashcardPersistenceStub;
import logic.FlashcardLogic;
import objects.Flashcard;

import static org.junit.Assert.assertEquals;

public class FlashcardLogicTest {
    private FlashcardLogic flashcardDB;

    @Before
    public void setUp(){
        flashcardDB = new FlashcardLogic(new FlashcardPersistenceStub());
    }

    @Test
    public void TestInsertFlashcard(){
        Flashcard flashcard = new Flashcard("Question5","Answer5","Guest1");
        // insert flashcard
        flashcardDB.insertFlashcard(flashcard);
        assertEquals(flashcardDB.getFlashcards().get(4).getQuestion(), flashcard.getQuestion());
        assertEquals(flashcardDB.getFlashcards().size(), 5);
        // insert duplicate
        flashcardDB.insertFlashcard(flashcard);
        assertEquals(flashcardDB.getFlashcards().get(5).getQuestion(), flashcard.getQuestion());
        assertEquals(flashcardDB.getFlashcards().size(), 6);
        // insert empty flashcard
        flashcardDB.insertFlashcard(new Flashcard("","",""));
        assertEquals(flashcardDB.getFlashcards().get(6).getQuestion(), "");
        assertEquals(flashcardDB.getFlashcards().size(), 7);
    }

    @Test
    public void TestGetFlashcardsAndSequential(){
        // default order of flashcards
        List<Flashcard> fList = flashcardDB.getFlashcards();
        // check the flashcards in sequential order
        for(int i = 0; i < fList.size(); i++){
            Flashcard flashcard = flashcardDB.getSequential();
            assertEquals(fList.get(i).getQuestion(), flashcard.getQuestion());
            assertEquals(fList.get(i).getAnswer(), flashcard.getAnswer());
            assertEquals(fList.get(i).getUserName(), flashcard.getUserName());
        }
    }


    @Test
    public void TestDeleteFlashcard(){
        // default order of flashcards
        List<Flashcard> fList = flashcardDB.getFlashcards();
        // delete the first card
        flashcardDB.deleteFlashcard(fList.get(0));
        // the first flashcard is now the default second flashcard
        assertEquals(flashcardDB.getSequential().getQuestion(), "This is a question");
        // check the size
        assertEquals(flashcardDB.getFlashcards().size(), 3);
        // delete all the cards
        int i = 3;
        while(flashcardDB.getFlashcards().size() > 0){
            // delete the first card
            flashcardDB.deleteFlashcard(fList.get(0));
            // check the size
            assertEquals(flashcardDB.getFlashcards().size(), --i);
        }
    }

    @Test
    public void TestGetSequential(){
        // default order of flashcards
        List<Flashcard> fList = flashcardDB.getFlashcards();
        // check the flashcards in sequential order
        for(int i = 0; i < fList.size(); i++){
            Flashcard flashcard = flashcardDB.getSequential();
            assertEquals(fList.get(i).getQuestion(), flashcard.getQuestion());
            assertEquals(fList.get(i).getAnswer(), flashcard.getAnswer());
            assertEquals(fList.get(i).getUserName(), flashcard.getUserName());
        }
    }

    @Test
    public void TestGetUserCards(){

    }


    @Test
    public void TestGetAllFolders(){

    }

    @Test
    public void TestInsertFolder(){
        // insert some folders
        // check if the folders exists
    }

    @Test
    public void TestInsertCardToFolder(){
        // make some flashcards
        // insert those flashcards to DB
        // assign those cards to folders
        // check if folder has those cards
        // check if the cards have the folders
    }

    @Test
    public void TestGetFolderCards(){
        // get all the cards from the folder
    }

    @Test
    public void TestDeleteFolder(){
        // delete the folder from the list
        // check if the folder exists
        // check the flashcards that is assigned to the folder that the folder is no longer there
    }

    @Test
    public void TestRemoveCardFromFolder(){
        // remove the card from the folder
    }
}
