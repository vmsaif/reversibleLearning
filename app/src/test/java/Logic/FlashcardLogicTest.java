package Logic;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import data.FlashcardPersistence;
import data.stubs.FlashcardPersistenceStub;
import logic.FlashcardLogic;
import objects.Flashcard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class FlashcardLogicTest {
    private FlashcardLogic flashcardDB;
    private FlashcardPersistence flashcardPersistence;

    @Before
    public void setUp(){
        flashcardDB = new FlashcardLogic(new FlashcardPersistenceStub());
        flashcardPersistence = mock(FlashcardPersistence.class);
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
        assertEquals(cardList.get(0), card1);
        assertEquals(cardList.get(1), card2);
        assertEquals(cardList.get(2), card3);
        // get from a user that doesn't exist
        cardList = flashcardDB.getUsersCards("NoSuchUsername");
        assertEquals(cardList.size(), 0);
        // delete the cards of "specificUser" and test it again
        flashcardDB.deleteFlashcard(card1);
        flashcardDB.deleteFlashcard(card2);
        flashcardDB.deleteFlashcard(card3);
        cardList = flashcardDB.getUsersCards(card1.getUserName());
        assertEquals(cardList.size(), 0);
    }


    @Test
    public void TestInsertFolderAndGetAllFolders(){
        // insert some folders
        flashcardDB.insertFolder("maths");
        flashcardDB.insertFolder("science");
        flashcardDB.insertFolder("language");
        // get all existing folders
        List<String> folderList = flashcardDB.getAllFolders();
        // check the folders exists
        assertTrue(folderList.contains("maths"));
        assertTrue(folderList.contains("science"));
        assertTrue(folderList.contains("language"));
        assertEquals(folderList.size(), 6);
        // insert duplicate folder
        flashcardDB.insertFolder("maths");
        // check the folders stays the same
        folderList = flashcardDB.getAllFolders();
        assertEquals(folderList.size(), 7);
    }

    @Test
    public void TestInsertCardToFolderAndGetFolderCards(){
        // make some flashcards
        Flashcard card1 = new Flashcard("card with quesiton 1", "answer 1", "user1");
        Flashcard card2 = new Flashcard("card with quesiton 2", "answer 2", "user1");
        Flashcard card3 = new Flashcard("card with quesiton 3", "answer 3", "user3");
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
        assertTrue(cardList.contains(card1));
        assertTrue(cardList.contains(card2));
        assertTrue(cardList.contains(card3));
        // check if the cards have the folders
        assertTrue(flashcardDB.getFlashcard(card1.getQuestion()).getFolderNames().contains("maths"));
        assertTrue(flashcardDB.getFlashcard(card2.getQuestion()).getFolderNames().contains("maths"));
        assertTrue(flashcardDB.getFlashcard(card3.getQuestion()).getFolderNames().contains("maths"));
    }


    @Test
    public void TestDeleteFolder(){
        // make some flashcards
        Flashcard card1 = new Flashcard("card with quesiton 1", "answer 1", "user1");
        Flashcard card2 = new Flashcard("card with quesiton 2", "answer 2", "user1");
        Flashcard card3 = new Flashcard("card with quesiton 3", "answer 3", "user3");
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
        // check the flashcards that is assigned to the folder that the folder is no longer there
        assertFalse(flashcardDB.getFlashcard(card1.getQuestion()).getFolderNames().contains("maths"));
        assertFalse(flashcardDB.getFlashcard(card2.getQuestion()).getFolderNames().contains("maths"));
        assertFalse(flashcardDB.getFlashcard(card3.getQuestion()).getFolderNames().contains("maths"));
    }

    @Test
    public void TestRemoveCardFromFolder(){
        // make some flashcards
        Flashcard card1 = new Flashcard("card with quesiton 1", "answer 1", "user1");
        Flashcard card2 = new Flashcard("card with quesiton 2", "answer 2", "user1");
        Flashcard card3 = new Flashcard("card with quesiton 3", "answer 3", "user3");
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
        assertEquals(cardList1.get(0), card2);
        // remove the rest of the cards from the folder
        flashcardDB.removeCardFromFolder(card2, "maths");
        flashcardDB.removeCardFromFolder(card3, "maths");
        cardList1 = flashcardDB.getFolderCards("maths");
        assertEquals(cardList1.size(), 0);
        assertFalse(flashcardDB.getFlashcard(card2.getQuestion()).getFolderNames().contains("maths"));
        assertFalse(flashcardDB.getFlashcard(card3.getQuestion()).getFolderNames().contains("maths"));
    }
}
