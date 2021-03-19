package objects;

import org.junit.Before;
import org.junit.Test;

import interfaces.IFlashcard;

import static org.junit.Assert.*;

public class FlashcardTest {
    //Variable
    private IFlashcard fCard;
    private IFlashcard gCard;

    @Before
    public void setUp(){
        gCard = new Flashcard("", "", "");
        fCard = new Flashcard("Question", "Answer", "Ali");
    }

    @Test
    public void TestInitialization() {
        // Test the constructor
        IFlashcard expected = new Flashcard("Question", "Answer", "Ali");
        assertTrue("The question should be the same",expected.getQuestion().equals(fCard.getQuestion()));
        assertTrue("The answer should be the same",expected.getAnswer().equals(fCard.getAnswer()));
        assertTrue("The username should be Ali",expected.getUserName().equals(fCard.getUserName()));
        // Test the constructor with "" as the string
        IFlashcard expected1 = new Flashcard("", "", "Guest");
        assertTrue("The question should be the same",expected1.getQuestion().equals(gCard.getQuestion()));
        assertTrue("The answer should be the same",expected1.getAnswer().equals(gCard.getAnswer()));
        assertTrue("The username should be Guest",expected1.getUserName().equals(gCard.getUserName()));
        assertEquals("The folder should be empty",gCard.getFolderNames().size(), 0);
    }


    @Test
    public void TestModifyQuestionAndReturnQuestion() {
        //Tests the methods modifyQuestion and returnQuestion
        IFlashcard expected = new Flashcard("Q", "A", "");
        expected.modifyQuestion("This is the question");
        fCard.modifyQuestion("This is the question");
        assertTrue("Modified and returned question should be same",expected.getQuestion().equals(fCard.getQuestion()));//Modify the question  and test it
        //Modify the answer again and test it
        expected.modifyQuestion("That is the question");
        assertFalse("Modified and returned question shouldn't be same",expected.getQuestion().equals(fCard.getQuestion()));
    }


    @Test
    public void TestModifyAnswerAndReturnAnswer() {
        //Tests the method modifyAnswer and getAnswer
        IFlashcard expected = new Flashcard("Q", "A", "");
        expected.modifyAnswer("This is the answer");
        fCard.modifyAnswer("This is the answer");
        assertTrue("Modified and returned answer should be same",expected.getAnswer().equals(fCard.getAnswer()));
        //Modify the answer again and test it
        expected.modifyAnswer("That is the answer");
        assertFalse("Modified and returned answer shouldn't be same",expected.getAnswer().equals(fCard.getAnswer()));
    }

    @Test
    public void TestFolderName(){
        // check both folders are empty
        assertEquals("The folder name is the same", fCard.getFolderNames().size(), 0);
        assertEquals("The folder name is the same", gCard.getFolderNames().size(), 0);
        // add folders
        gCard.addFolderName("folder1");
        fCard.addFolderName("folder1");
        assertEquals("The folder name is the same", fCard.getFolderNames(), gCard.getFolderNames());
        // add more folder names
        gCard.addFolderName("folder2");
        fCard.addFolderName("folder2");
        assertEquals("The folder name is the same", fCard.getFolderNames(), gCard.getFolderNames());
        // check the content of the folder names
        for(int i = 0; i < fCard.getFolderNames().size(); i++){
            assertEquals("The names in the folder are equals", fCard.getFolderNames().get(i), gCard.getFolderNames().get(i));
        }
        // add  folder names to fCard which will make fCard != gCard
        fCard.addFolderName("folder3");
        assertNotEquals("The folder is not equal to one another", fCard.getFolderNames(), gCard.getFolderNames());
    }

    @Test
    public void TestFavorite() {
        // check the default favorite == false
        assertFalse("The flashcard is not a favorite", fCard.getIsFavorite());
        // set to favorite
        fCard.setIsFavorite(true);
        // check the favorite == true
        assertTrue("The flashcard is not a favorite", fCard.getIsFavorite());
    }

}