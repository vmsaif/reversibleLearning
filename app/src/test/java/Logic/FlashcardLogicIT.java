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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


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
        assertEquals(flashcardDB.getFlashcards().size(), 10);
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

    @Test
    public void TestGetUserCards(){
        // insert new cards from a specific user
        Flashcard card1 = new Flashcard("card question 1?", "answer 1", "specificUser");
        Flashcard card2 = new Flashcard("card question 2?", "answer 2", "specificUser");
        Flashcard card3 = new Flashcard("card question 3?", "answer 3", "specificUser");
        flashcardDB.insertFlashcard(card1);
        flashcardDB.insertFlashcard(card2);
        flashcardDB.insertFlashcard(card3);
        // get all the cards from the user
        List<Flashcard> cardList = flashcardDB.getUsersCards(card1.getUserName());
        // check the cards
        assertEquals(cardList.size(), 3);
        assertEquals(cardList.get(0).getQuestion(), card1.getQuestion());
        assertEquals(cardList.get(0).getAnswer(), card1.getAnswer());
        assertEquals(cardList.get(1).getQuestion(), card2.getQuestion());
        assertEquals(cardList.get(1).getAnswer(), card2.getAnswer());
        assertEquals(cardList.get(2).getQuestion(), card3.getQuestion());
        assertEquals(cardList.get(2).getAnswer(), card3.getAnswer());
    }

    @Test
    public void TestInsertFolderAndGetAllFolders(){
        // insert some folders
        flashcardDB.insertFolder("maths");
        flashcardDB.insertFolder("science");
        // get all existing folders
        List<String> folderList = flashcardDB.getAllFolders();
        // check the folders exists
        assertTrue(folderList.contains("maths"));
        assertTrue(folderList.contains("science"));
        assertEquals(folderList.size(), 3);
    }

    @Test
    public void TestInsertCardToFolderAndGetFolderCards(){
        // make some flashcards
        Flashcard card1 = new Flashcard("card with question 1", "answer 1", "user1");
        Flashcard card2 = new Flashcard("card with question 2", "answer 2", "user1");
        Flashcard card3 = new Flashcard("card with question 3", "answer 3", "user3");
        // insert those flashcards to DB
        flashcardDB.insertFlashcard(card1);
        flashcardDB.insertFlashcard(card2);
        flashcardDB.insertFlashcard(card3);
        flashcardDB.insertFolder("maths");
        // assign those cards to folders
        flashcardDB.insertCardToFolder(card1, "maths");
        flashcardDB.insertCardToFolder(card2, "maths");
        flashcardDB.insertCardToFolder(card3, "maths");
        // check if folder has those cards
        List<Flashcard> cardList = flashcardDB.getFolderCards("maths");
        assertEquals(cardList.size(), 3);
        assertEquals(cardList.get(0).getQuestion(), card1.getQuestion());
        assertEquals(cardList.get(1).getQuestion(), card2.getQuestion());
        assertEquals(cardList.get(2).getQuestion(), card3.getQuestion());
        // check if the cards have the folders
        assertTrue(flashcardDB.getFlashcard(card1.getQuestion()).getFolderNames().contains("maths"));
        assertTrue(flashcardDB.getFlashcard(card2.getQuestion()).getFolderNames().contains("maths"));
        assertTrue(flashcardDB.getFlashcard(card3.getQuestion()).getFolderNames().contains("maths"));
    }

    @Test
    public void TestDeleteFolder(){
        // make some flashcards
        Flashcard card1 = new Flashcard("card with question 1", "answer 1", "user1");
        Flashcard card2 = new Flashcard("card with question 2", "answer 2", "user1");
        Flashcard card3 = new Flashcard("card with question 3", "answer 3", "user3");
        // insert those flashcards to DB
        flashcardDB.insertFlashcard(card1);
        flashcardDB.insertFlashcard(card2);
        flashcardDB.insertFlashcard(card3);
        flashcardDB.insertFolder("maths");
        // assign those cards to folders
        flashcardDB.insertCardToFolder(card1, "maths");
        flashcardDB.insertCardToFolder(card2, "maths");
        flashcardDB.insertCardToFolder(card3, "maths");
        // check it is correct
        List<Flashcard> cardList1 = flashcardDB.getFolderCards("maths");
        assertEquals(cardList1.size(), 3);
        // delete the folder from the list
        flashcardDB.deleteFolder("maths");
        // check if the folder exists
        assertFalse(flashcardDB.getAllFolders().contains("maths"));
    }

    @Test
    public void TestRemoveCardFromFolder(){
        // make some flashcards
        Flashcard card1 = new Flashcard("card with question 1", "answer 1", "user1");
        Flashcard card2 = new Flashcard("card with question 2", "answer 2", "user1");
        Flashcard card3 = new Flashcard("card with question 3", "answer 3", "user3");
        // insert those flashcards to DB
        flashcardDB.insertFlashcard(card1);
        flashcardDB.insertFlashcard(card2);
        flashcardDB.insertFlashcard(card3);
        flashcardDB.insertFolder("maths");
        // assign those cards to folders
        flashcardDB.insertCardToFolder(card1, "maths");
        flashcardDB.insertCardToFolder(card2, "maths");
        flashcardDB.insertCardToFolder(card3, "maths");
        // check it is correct
        List<Flashcard> cardList1 = flashcardDB.getFolderCards("maths");
        assertEquals(cardList1.size(), 3);
        // remove the card from the folder and check it
        flashcardDB.removeCardFromFolder(card1, "maths");
        cardList1 = flashcardDB.getFolderCards("maths");
        assertEquals(cardList1.size(), 2);
        assertFalse(flashcardDB.getFlashcard(card1.getQuestion()).getFolderNames().contains("maths"));
        assertEquals(cardList1.get(0).getQuestion(), card2.getQuestion());
    }

    @After
    public void tearDown(){
        tempDB.deleteOnExit();
        Services.clean();
    }
}