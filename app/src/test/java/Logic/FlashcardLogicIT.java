package Logic;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    public void Test1InsertFlashcard(){
        Flashcard flashcard = new Flashcard("Question5","Answer5","Guest1");
        // insert flashcards
        flashcardDB.insertFlashcard(flashcard);
        // check if insert is successful
        assertEquals(flashcardDB.getFlashcards().get(4).getQuestion(), flashcard.getQuestion());
        assertEquals(flashcardDB.getFlashcards().size(), 7);
    }

    @Test
    public void Test2GetFlashcardsAndSequential(){
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
    public void Test3DeleteFlashcard(){
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
    public void Test4GetUserCards(){
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
    public void Test5InsertFolderAndGetAllFolders(){
        // insert some folders
        flashcardDB.insertFolder("maths");
        flashcardDB.insertFolder("science");
        // get all existing folders
        List<String> folderList = flashcardDB.getAllFolders();
        // check the folders exists
        assertTrue(folderList.contains("maths"));
        assertTrue(folderList.contains("science"));
        assertEquals(folderList.size(), 4);
    }

    @Test
    public void Test6InsertCardToFolderAndGetFolderCards(){
        // make some flashcards
        Flashcard card1 = new Flashcard("card with question 11", "answer 1", "user1");
        Flashcard card2 = new Flashcard("card with question 22", "answer 2", "user1");
        Flashcard card3 = new Flashcard("card with question 33", "answer 3", "user3");
        // insert those flashcards to DB
        flashcardDB.insertFlashcard(card1);
        flashcardDB.insertFlashcard(card2);
        flashcardDB.insertFlashcard(card3);
        // assign those cards to folders
        flashcardDB.insertCardToFolder(card1, "arts");
        flashcardDB.insertCardToFolder(card2, "arts");
        flashcardDB.insertCardToFolder(card3, "arts");
        // check if folder has those cards
        List<Flashcard> cardList = flashcardDB.getFolderCards("arts");
        assertEquals(cardList.size(), 4);
        assertEquals(cardList.get(0).getQuestion(), "Question4");
        assertEquals(cardList.get(1).getQuestion(), card1.getQuestion());
        assertEquals(cardList.get(2).getQuestion(), card2.getQuestion());
        assertEquals(cardList.get(3).getQuestion(), card3.getQuestion());
    }

    @Test
    public void Test7RemoveCardFromFolder(){
        // get a card
        List<Flashcard> cardList1 = flashcardDB.getFolderCards("arts");
        Flashcard card1 = cardList1.get(0);
        // remove the card from the folder
        flashcardDB.removeCardFromFolder(card1, "arts");
        cardList1 = flashcardDB.getFolderCards("arts");
        // check that the card is no longer in the folder
        assertEquals(cardList1.size(), flashcardDB.getFolderCards("arts").size());
    }

    @Test
    public void Test8DeleteFolder(){
        // make some flashcards
        Flashcard card1 = new Flashcard("card with question 111", "answer 1", "user1");
        Flashcard card2 = new Flashcard("card with question 222", "answer 2", "user1");
        Flashcard card3 = new Flashcard("card with question 333", "answer 3", "user3");
        // insert those flashcards to DB
        flashcardDB.insertFlashcard(card1);
        flashcardDB.insertFlashcard(card2);
        flashcardDB.insertFlashcard(card3);
        // assign those cards to folders
        flashcardDB.insertCardToFolder(card1, "arts");
        flashcardDB.insertCardToFolder(card2, "arts");
        flashcardDB.insertCardToFolder(card3, "arts");
        // delete the folder from the list
        flashcardDB.deleteFolder("arts");
        // check if the folder exists
        assertFalse(flashcardDB.getAllFolders().contains("arts"));
    }


    @After
    public void tearDown(){
        tempDB.deleteOnExit();
        Services.clean();
    }
}