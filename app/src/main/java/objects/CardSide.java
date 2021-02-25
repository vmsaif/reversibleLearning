package objects;
import interfaces.ICardSide;

public class CardSide implements ICardSide{

    //variables
    private String whichSide;
    private String questionOrAns = "";

    //constructor---create a CardSide object given which side of the card is this
    public CardSide(String whichSide){
        this.whichSide = whichSide;
    }//constructor


    //addText---adding question or answer to this side
    public void addText(String qAndA){
        questionOrAns = qAndA;
    }//addText


    //getText
    public String getText(){
        return questionOrAns;
    }//getText


    //getSide---tells if this is the front or the back side
    public String getSide(){
        return whichSide;
    }//getSide


    //showSide---will show the card side with the text on it (will be modified for UI but for now just prints the text)
    public void showSide(){
        System.out.println(questionOrAns);
    }//showSide


}//CardSide class

