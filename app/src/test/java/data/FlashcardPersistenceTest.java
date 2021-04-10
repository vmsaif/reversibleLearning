package data;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import data.stubs.FlashcardPersistenceStub;
import objects.Flashcard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
    public void TestInsertFolder(){
        Flashcard newFlashcard = new Flashcard("question123", "answer1321", "newUser1");
        Flashcard existFlashcard = new Flashcard("question", "answer", "group5");
        // insert folders to an existing flashcard
        db.insertFolder("folder1");
        // check the folder is inserted
        Flashcard foundCard = db.getFlashcardSequential().get(2);
        // check the content
        assertEquals(foundCard.getQuestion(), existFlashcard.getQuestion());
        assertEquals(foundCard.getAnswer(), existFlashcard.getAnswer());
        assertEquals(foundCard.getUserName(), existFlashcard.getUserName());
        assertEquals(foundCard.getFolderNames().size(), 1);
        assertEquals(foundCard.getFolderNames().get(0), "folder1");
        // insert duplicate folder
        db.insertFolder("folder1");
        // check the folder is inserted
        foundCard = db.getFlashcardSequential().get(2);
        assertEquals(foundCard.getFolderNames().size(), 2);
        assertEquals(foundCard.getFolderNames().get(1), "folder1");
        // insert more folders
        db.insertFolder("folder2");
        db.insertFolder("folder3");
        // check the folder is inserted
        foundCard = db.getFlashcardSequential().get(2);
        assertEquals(foundCard.getFolderNames().size(), 4);
        assertEquals(foundCard.getFolderNames().get(3), "folder3");
        // insert new flashcard
        db.insertFolder("folder1");
        // check the folder is inserted by checking the size
        assertEquals(db.getFlashcardSequential().size(), 4);
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
    public void TestGetAllFolders(){

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
