package objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardSideTest {

    private CardSide objFront;
    //private CardSide objBack;
    @Before
    public  void setUp(){
        objFront = new CardSide("Front");
        //objBack = new CardSide("Back");
    }

    @Test
    public void TestGetSide() {
        String expected = "Front";
        String actual = objFront.getSide();
        assertEquals("Side of a card should be equal to expected",expected,actual);
    }

    @Test
    public void TestAddTextAndGetText() {
        String expectedString = "What's the capital of Canada?";
        objFront.addText("What's the capital of Canada?");
        String output = objFront.getText();
        assertEquals("Front text of card should be equal to expected.",expectedString,output);

    }
    /*@Test
    public void showSide() {

    }*/
}