package Logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import flashcard.group5.application.Services;
import logic.FlashcardLogic;
import objects.Flashcard;
import utils.TestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class FlashcardLogicIT {
    private File tempDB;
    private FlashcardLogic flashcardDB;

    @Before
    public void setUp() throws Exception{
        tempDB = TestUtils.copyDB();
        flashcardDB = new FlashcardLogic();
        assertNotNull(flashcardDB);
    }

    @Test
    public void TestInsertFlashcard(){
        Flashcard flashcard = new Flashcard("Question5","Answer5","Guest1");
        // insert flashcards
        flashcardDB.insertFlashcard(flashcard);
        // check if insert is successful
        assertEquals(flashcardDB.getFlashcards().get(4).getQuestion(), flashcard.getQuestion());
        assertEquals(flashcardDB.getFlashcards().size(), 6);
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
        // get the new first card
        Flashcard flashcard = flashcardDB.getSequential();
        // the first flashcard is now the default second flashcard
        assertEquals(flashcardDB.getSequential().getQuestion(), fList.get(1).getQuestion());
    }

    @After
    public void tearDown(){
        tempDB.deleteOnExit();
        Services.clean();
    }
}