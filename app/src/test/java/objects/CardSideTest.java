package objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardSideTest {

    private CardSide objFront;
    private CardSide objBack;
    @Before
    public  void setUp(){
        objFront = new CardSide("Front");
        objBack = new CardSide("Back");
    }

    @Test
    public void TestGetSide() {
        //Gets the front side
        String expected = "Front";
        String actual = objFront.getSide();
        assertEquals("Side of a card should be equal to expected",expected,actual);
        //Gets the back side
        String expected2 = "Back";
        String actual2 = objBack.getSide();
        assertEquals("Side of a card should be equal to expected",expected2,actual2);
    }

    @Test
    public void TestAddTextAndGetText() {
        //Add text to the front
        String expectedString = "What's the capital of Canada?";
        objFront.addText("What's the capital of Canada?");
        String output = objFront.getText();
        assertEquals("Front text of card should be equal to expected.",expectedString,output);
        // add text to the back
        String expectedString2 = "The capital of Canada is Ottawa";
        objBack.addText("The capital of Canada is Ottawa");
        String output2 = objBack.getText();
        assertEquals("Back text of card should be equal to expected.",expectedString2,output2);

    }
}