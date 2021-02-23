package objects;
import java.util.ArrayList;

public class FlashcardList {

    //variables
    private Flashcard card;
    private ArrayList<Flashcard> list;

    //constructor
    public FlashcardList () {
        //initializes the list with no new flashcards
        list = new ArrayList<>();
    }

    //add method to add a flashcard to the end of the list(So the list will be sorted by time added)
    public void addCard (Flashcard toAdd) {
        list.add(toAdd);
    }

    public void removeCard(int i) {
        list.remove(i);
    }

    //given a flashcard's index, getCard will return the flashcard asked for
    public Flashcard getCard (int i) {
        Flashcard toReturn = list.get(i);

        return toReturn;
    }

    //same as above, will change a flashcard object at given index to new flashcard
    public void changeCard (int i, Flashcard toChange){
        list.set(i, toChange);
    }

}
