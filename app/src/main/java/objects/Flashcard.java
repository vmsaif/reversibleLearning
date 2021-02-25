package objects;
import interfaces.IFlashcard;

public class Flashcard implements IFlashcard{

    //variables
    private CardSide front;
    private CardSide back;


    //constructor---will create a Flashcard object that has two sides (CardSide) front and back
    public Flashcard(){
        front = new CardSide("front"); //front side of the flash card will have the question on it
        back = new CardSide("back"); //back side of the flash card will have the answer
    }//constructor


    //modifyQuestion---can edit the question
    public void modifyQuestion(String question){
        front.addText(question);
    }//modifyQuestion


    //modifyAnswer---can edit the answers
    public void modifyAnswer(String answer){
        back.addText(answer);
    }//modifyAnswer


    //returnQuestion---return the question string
    public String returnQuestion(){
        return front.getText();
    }//returnQuestion


    //returnAnswer---return the answer string
    public String returnAnswer(){
        return back.getText();
    }//returnAnswer


    //showFlashcard---will show the front side initially but with a click the side will flip
    public void showFlashcard(boolean isFront){
        //while implementing this in the logic layer I will call a mouse click event.
        //Every click will cause a boolean to switch its state and that will be passed on to this method
        //so if a boolean isFront (in the logic layer) is true, this will show the front side of the card
        //if I click the card, it will change the isFront boolean to false and show the back side of the card
        if(isFront){
            front.showSide();
        }//if front side
        else{
            back.showSide();
        }//else
    }//showFlashcard


}//Flashcard class

