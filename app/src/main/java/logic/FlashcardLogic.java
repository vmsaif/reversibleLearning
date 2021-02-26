package logic;

import android.os.Parcel;
import android.os.Parcelable;

import objects.Flashcard;
import presentation.FlashcardActivity;
import presentation.CardviewActivity;

public class FlashcardLogic{

    //variables
    Flashcard fCard;

    //constructor
    public FlashcardLogic(String question, String answer){
        fCard = new Flashcard();
        fCard.modifyQuestion(question);
        fCard.modifyAnswer(answer);
    }//constructor


    //getQuestion---calls the returnQuestion method
    public String getQuestion(){
        return fCard.returnQuestion();
    }//getQuestion


    //getAnswer---calls the returnAnswer method
    public String getAnswer(){
        return fCard.returnAnswer();
    }//getAnswer

}//FlashcardLogic class
