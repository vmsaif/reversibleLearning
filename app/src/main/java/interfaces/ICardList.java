package interfaces;
import interfaces.IFlashcard;

public interface ICardList {

    //add method to add a flashcard to the end of the list(So the list will be sorted by time added)
    void addCard (IFlashcard toAdd);

    void removeCard(int i);

    //given a flashcard's index, getCard will return the flashcard asked for
    IFlashcard getCard (int i);

    //same as above, will change a flashcard object at given index to new flashcard
    void changeCard (int i, IFlashcard toChange);

}
