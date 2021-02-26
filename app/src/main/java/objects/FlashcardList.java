package objects;
import java.util.ArrayList;
import interfaces.IFlashcard;

public class FlashcardList {

    //variables
    private IFlashcard card;
    private ArrayList<IFlashcard> list;

    //constructor
    public FlashcardList () {
        //initializes the list with no new flashcards
        list = new ArrayList<>();
    }

    //add method to add a flashcard to the end of the list(So the list will be sorted by time added)
    public void addCard (IFlashcard toAdd) {
        list.add(toAdd);
    }

    public void removeCard(int i) {
        list.remove(i);
    }

    //given a flashcard's index, getCard will return the flashcard asked for
    public IFlashcard getCard (int i) {
        IFlashcard toReturn = list.get(i);

        return toReturn;
    }

    //same as above, will change a flashcard object at given index to new flashcard
    public void changeCard (int i, IFlashcard toChange){
        list.set(i, toChange);
    }

    public int getSize(){
        return list.size();
    }
}
