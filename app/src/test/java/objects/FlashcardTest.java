package objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlashcardTest {
    private Flashcard FrontFlash;
    private Flashcard BackFlash;
    @Before
    public void setUp(){
        FrontFlash = new Flashcard();
        BackFlash = new Flashcard();

    }

    @Test
    public void TestModifyQuestionAndReturnQuestion() {
        //Tests the methods modifyQuestion and returnQuestion
        Flashcard expected = new Flashcard();
        expected.modifyQuestion("This is the question");
        FrontFlash.modifyQuestion("This is the question");
        assertTrue("Modified and returned question should be same",expected.returnQuestion().equals(BackFlash.returnQuestion()));//Modify the question again and test it
        expected.modifyAnswer("That is the question");
        assertFalse("Modified and returned question shouldn't be same",expected.returnQuestion().equals(FrontFlash.returnQuestion()));

    }

    @Test
    public void TestModifyAnswerAndReturnAnswer() {
        //Tests the method modifyAnswer and returnAnswer
        Flashcard expected = new Flashcard();
        expected.modifyAnswer("This is the answer");
        BackFlash.modifyAnswer("This is the answer");
        assertTrue("Modified and returned answer should be same",expected.returnAnswer().equals(BackFlash.returnAnswer()));
        //Modify the answer again and test it
        expected.modifyAnswer("That is the answer");
        assertFalse("Modified and returned answer shouldn't be same",expected.returnAnswer().equals(BackFlash.returnAnswer()));
    }

    /*@Test
    public void showFlashcard() {
    }*/
}