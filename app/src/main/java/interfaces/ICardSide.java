package interfaces;

public interface ICardSide {

    //addText---adding question or answer to this side
    void addText(String qAndA);

    //getText
    String getText();

    //getSide---tells if this is the front or the back side
    String getSide();

    //showSide---will show the card side with the text on it (will be modified for UI but for now just prints the text)
    void showSide();

}
