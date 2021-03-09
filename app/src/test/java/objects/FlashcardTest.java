package objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlashcardTest {
    //Variable
    private Flashcard fCard;

    @Before
    public void setUp(){
        fCard = new Flashcard("Question", "Answer", "Ali");
    }

    @Test
    public void TestModifyQuestionAndReturnQuestion() {
        //Tests the methods modifyQuestion and returnQuestion
        Flashcard expected = new Flashcard("Q", "A", "");
        expected.modifyQuestion("This is the question");
        fCard.modifyQuestion("This is the question");
        assertTrue("Modified and returned question should be same",expected.getQuestion().equals(fCard.getQuestion()));//Modify the question again and test it

    }


    @Test
    public void TestModifyAnswerAndReturnAnswer() {
        //Tests the method modifyAnswer and getAnswer
        Flashcard expected = new Flashcard("Q", "A", "");
        expected.modifyAnswer("This is the answer");
        fCard.modifyAnswer("This is the answer");
        assertTrue("Modified and returned answer should be same",expected.getAnswer().equals(fCard.getAnswer()));
        //Modify the answer again and test it
        expected.modifyAnswer("That is the answer");
        assertFalse("Modified and returned answer shouldn't be same",expected.getAnswer().equals(fCard.getAnswer()));
    }

}