package data;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import data.stubs.FlashcardPersistenceStub;
import objects.Flashcard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class FlashcardPersistenceTest {

    private FlashcardPersistence db;

    @Before
    public void setUp() {
        db = new FlashcardPersistenceStub();
    }

    @Test
    public void TestGetFlashcardSequential(){
        List<Flashcard> flashcards = db.getFlashcardSequential();
        // check the size
        assertEquals(flashcards.size(), 4);
        // check the content
        assertEquals(flashcards.get(2).getQuestion(), "question");
        assertEquals(flashcards.get(2).getAnswer(), "answer");
        assertEquals(flashcards.get(2).getUserName(), "group5");
    }

    @Test
    public void TestInsertFlashcard(){
        // insert a flashcard
        Flashcard flashcard = new Flashcard("random question", "random answer", "randUser");
        db.insertFlashcard(flashcard);
        // find the user to see if it is successfully inserted
        Flashcard foundCard = db.getFlashcardSequential().get(4);
        // check the content
        assertEquals(foundCard.getQuestion(), flashcard.getQuestion());
        assertEquals(foundCard.getAnswer(), flashcard.getAnswer());
        assertEquals(foundCard.getUserName(), flashcard.getUserName());
        assertEquals(db.getFlashcardSequential().size(), 5);
        // insert a duplicate
        db.insertFlashcard(flashcard);
        foundCard = db.getFlashcardSequential().get(5);
        // check the content
        assertEquals(foundCard.getQuestion(), flashcard.getQuestion());
        assertEquals(foundCard.getAnswer(), flashcard.getAnswer());
        assertEquals(foundCard.getUserName(), flashcard.getUserName());
        assertEquals(db.getFlashcardSequential().size(), 6);
    }

    @Test
    public void TestDeleteFlashcard(){
        List<Flashcard> flashcards = db.getFlashcardSequential();
        // delete every flashcard
        for(int i = 0; i < 4; i++) {
            db.deleteFlashcard(flashcards.get(0));
            // check the size
            assertEquals(db.getFlashcardSequential().size(), 3-i);
        }
    }

    @Test
    public void TestInsertFolderAndGetAllFolders(){
        // insert folders to the database
        db.insertFolder("folder4");
        db.insertFolder("folder5");
        // check the folder is inserted
        List<String> folders = db.getAllFolders();
        // check the content
        assertEquals(folders.get(0), "folder1");
        assertEquals(folders.get(1), "folder2");
        assertEquals(folders.get(2), "folder13");
        assertEquals(folders.get(3), "folder4");
        assertEquals(folders.get(4), "folder5");
    }


    @Test
    public void TestGetFlashcardFolders(){
        Flashcard newFlashcard = new Flashcard("newQuestion1", "newAnswer1", "newUser1");
        Flashcard existFlashcard = new Flashcard("question1", "answer1", "user1");
        // get the folders
        List<String> folders = db.getFlashcardFolders(existFlashcard);
        // check the size and content
        assertEquals(folders.size(), 3);
        assertEquals(folders.get(0), "folder1");
        assertEquals(folders.get(1), "folder2");
        assertEquals(folders.get(2), "folder13");
        // add a new folder
        db.insertFolder("newFolder");
        db.insertCardToFolder(existFlashcard, "newFolder");
        // check the new folder
        folders = db.getFlashcardFolders(existFlashcard);
        // check the size and content
        assertEquals(folders.size(), 4);
        assertEquals(folders.get(3), "newFolder");
        // get the folders from a non-existent flashcard
        folders = db.getFlashcardFolders(newFlashcard);
        // check if exists
        assertNull(folders);
    }


    @Test
    public void TestInsertCardToFolder(){
        // get some flashcards from the DB
        Flashcard card1 = db.getFlashcardSequential().get(1);
        Flashcard card2 = db.getFlashcardSequential().get(2);
        // create a new flashcard
        Flashcard card3 = new Flashcard("zzzQuestionzzz", "zzzAnswerzzz", "ZGENUSER");
        // insert the new flashcard to the db
        db.insertFlashcard(card3);
        // get the list of existing folders
        List<String> folders = db.getAllFolders();
        // assign those cards to folders
        db.insertCardToFolder(card1, folders.get(0));
        db.insertCardToFolder(card1, folders.get(1));
        db.insertCardToFolder(card1, folders.get(2));
        db.insertCardToFolder(card2, folders.get(0));
        db.insertCardToFolder(card3, folders.get(0));
        // check if folder has those cards
        List<Flashcard> cardInFolder = db.getFolderCards(folders.get(0));
        assertTrue(cardInFolder.contains(card1));
        assertTrue(cardInFolder.contains(card2));
        assertTrue(cardInFolder.contains(card3));
        // check if the cards have the folders
        card1 = db.getFlashcardSequential().get(1);
        assertEquals(card1.getFolderNames().size(), 3);
        assertEquals(card1.getFolderNames().get(0), folders.get(0));
        assertEquals(card1.getFolderNames().get(1), folders.get(1));
        assertEquals(card1.getFolderNames().get(2), folders.get(2));
        card2 = db.getFlashcardSequential().get(2);
        assertEquals(card2.getFolderNames().size(), 1);
        assertEquals(card2.getFolderNames().get(0), folders.get(0));
        card3 = db.getFlashcard(card3.getQuestion());
        assertEquals(card3.getFolderNames().size(), 1);
        assertEquals(card3.getFolderNames().get(0), folders.get(0));
    }

    @Test
    public void TestGetFolderCards(){
        String folderName = "very new folder";
        // make a new folder
        db.insertFolder(folderName);
        // assign the cards to a folder
        for(int i = 0; i < db.getAllFlashcards().size(); i++){
            db.insertCardToFolder(db.getFlashcardSequential().get(i), folderName);
        }
        // get the folder cards
        List<Flashcard> cards = db.getFolderCards(folderName);
        // check that the getFolderCards function is working
        for(int i = 0; i < db.getAllFlashcards().size(); i++){
            assertEquals(db.getFlashcardSequential().get(i), cards.get(i));
        }

    }

    @Test
    public void TestDeleteFolder(){
        // get all existing folders
        List<String> folders = db.getAllFolders();
        String toBeDeleted = folders.get(0);
        // add some cards to the folder
        for(int i = 0; i < db.getAllFlashcards().size(); i++){
            db.insertCardToFolder(db.getFlashcardSequential().get(i), toBeDeleted);
        }
        // get all the cards in the folder
        List<Flashcard> cards = db.getFolderCards(toBeDeleted);
        // get the total number of all the folders
        int totalFolders = folders.size();
        // delete the folder
        db.deleteFolder(toBeDeleted);
        // check if the folder exists
        folders = db.getAllFolders();
        assertEquals(totalFolders-1, db.getAllFolders().size());
        assertFalse(folders.contains(toBeDeleted));
        // check the flashcards that is assigned to the folder that the folder is no longer there
        for(int i = 0; i < db.getAllFlashcards().size(); i++){
            assertFalse(db.getFlashcardSequential().get(0).getFolderNames().contains(toBeDeleted));
        }
    }

    @Test
    public void TestRemoveCardFromFolder(){
        // get all existing folders
        List<String> folders = db.getAllFolders();
        String folderName = folders.get(0);
        // assign the folderName to all cards
        for(int i = 0; i < db.getAllFlashcards().size(); i++){
            db.insertCardToFolder(db.getFlashcardSequential().get(i), folderName);
        }
        // remove the folderName from all the cards using the removeCardFromFolder function
        for(int i = 0; i < db.getAllFlashcards().size(); i++){
            db.removeCardFromFolder(db.getFlashcardSequential().get(i), folderName);
        }
        // check if the folder exists and is empty
        assertTrue(db.getAllFolders().contains(folderName));
        assertEquals(db.getFolderCards(folderName).size(), 0);
        // check the flashcards that is assigned to the folder that the folder is no longer there
        for(int i = 0; i < db.getAllFlashcards().size(); i++){
            assertFalse(db.getFlashcardSequential().get(0).getFolderNames().contains(folderName));
        }
    }

}
