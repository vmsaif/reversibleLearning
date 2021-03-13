package objects;

import org.junit.Before;
import org.junit.Test;

import interfaces.IFlashcard;

import static org.junit.Assert.*;

public class FlashcardTest {
    //Variable
    private IFlashcard fCard;

    @Before
    public void setUp(){
        fCard = new Flashcard("Question", "Answer", "Ali");
    }

    @Test
    public void TestInitialization() {
        //Tests the constructor
        IFlashcard expected = new Flashcard("Question", "Answer", "Ali");
        assertTrue("The question should be the same",expected.getQuestion().equals(fCard.getQuestion()));
        assertTrue("The answer should be the same",expected.getAnswer().equals(fCard.getAnswer()));
    }

    @Test
    public void TestModifyQuestionAndReturnQuestion() {
        //Tests the methods modifyQuestion and returnQuestion
        IFlashcard expected = new Flashcard("Q", "A", "");
        expected.modifyQuestion("This is the question");
        fCard.modifyQuestion("This is the question");
        assertTrue("Modified and returned question should be same",expected.getQuestion().equals(fCard.getQuestion()));//Modify the question  and test it
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

}