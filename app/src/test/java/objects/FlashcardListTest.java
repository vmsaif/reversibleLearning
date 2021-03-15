package objects;
import org.junit.Before;
import org.junit.Test;

import interfaces.IFlashcard;

import static org.junit.Assert.*;

public class FlashcardListTest {

    private FlashcardList list;

    @Before
    public  void setUp() {
        list = new FlashcardList();
    }

    @Test
    public void TestAddCard() {
        IFlashcard card1 = new Flashcard("Question", "Answer", "Ali");
        IFlashcard card2 = new Flashcard("Question1", "Answer2", "");
        //Test the addCard method
        list.addCard(card1);
        list.addCard(card2);
        assertEquals("The original flashcard and the card in the flashcard list are the same", list.getCard(0), card1);
        assertEquals("The original flashcard and the card in the flashcard list are the same", list.getCard(1), card2);
    }

    @Test
    public void TestRemoveCard() {
        IFlashcard card1 = new Flashcard("Question", "Answer", "Ali");
        IFlashcard card2 = new Flashcard("Question1", "Answer2", "");
        //add cards to be tested
        list.addCard(card1);
        list.addCard(card2);
        //Test the removeCard method
        list.removeCard(0);
        assertEquals("The second card is now the first card", list.getCard(0), card2);
        assertEquals("The size is 1", list.getSize(), 1);
        list.removeCard(0);
        assertEquals("The size is 0", list.getSize(), 0);
    }

    @Test
    public void TestGetCardAndGetSize() {
        IFlashcard card1 = new Flashcard("Question", "Answer", "Ali");
        IFlashcard card2 = new Flashcard("Question1", "Answer2", "");
        IFlashcard card3 = new Flashcard("Question4", "Answer5", "");

        //Test the getCard method and the getSize method
        list.addCard(card1);
        assertEquals("The card is the same", list.getCard(0), card1);
        assertEquals("The size is 1", list.getSize(), 1);
        list.addCard(card2);
        assertEquals("The first card in the list is unmodified", list.getCard(0), card1);
        assertEquals("The card is the same", list.getCard(1), card2);
        assertEquals("The size is 2", list.getSize(), 2);
        list.addCard(card3);
        assertEquals("The card is the same", list.getCard(2), card3);
        assertEquals("The size is 3", list.getSize(), 3);
    }

    @Test
    public void TestChangeCard() {
        IFlashcard card1 = new Flashcard("Question", "Answer", "Ali");
        IFlashcard card2 = new Flashcard("Question1", "Answer2", "");
        IFlashcard card3 = new Flashcard("Question4", "Answer5", "");

        //add a card
        list.addCard(card1);
        //test the changeCard method
        list.changeCard(0, card2);
        assertEquals("The card is now card2", list.getCard(0), card2);
        assertEquals("The size is 1", list.getSize(), 1);
        list.changeCard(0, card3);
        assertEquals("The card is now card3", list.getCard(0), card3);
        assertEquals("The size is 1", list.getSize(), 1);
    }
}
