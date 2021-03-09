package logic;

import objects.Flashcard;

public class FlashcardLogic{

    //variables
    Flashcard fCard;

    //constructor
    public FlashcardLogic(String question, String answer){
        fCard = new Flashcard(question, answer, "");
    }//constructor


    //getQuestion---calls the returnQuestion method
    public String getQuestion(){
        return fCard.getQuestion();
    }//getQuestion


    //getAnswer---calls the getAnswer method
    public String getAnswer(){
        return fCard.getAnswer();
    }//getAnswer

}//FlashcardLogic class
