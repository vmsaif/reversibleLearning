package objects;
import interfaces.IFlashcard;

public class Flashcard implements IFlashcard{

    class CardSide{ //nested class since this is only used inside the flashcard object

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

}//Flashcard class

