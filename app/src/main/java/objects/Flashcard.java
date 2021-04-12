package objects;
import java.util.ArrayList;
import java.util.List;

import interfaces.IFlashcard;

public class Flashcard implements IFlashcard{

    //variables
    private String question;
    private String answer;
    private String userName;
    private List<String> folderNames; //list of all the folders this flashcard is associated with
    boolean isFavorite;


    //constructor---will create a Flashcard object that has two sides (CardSide) front and back
    public Flashcard(String question, String answer, String userName){
        this.question = question;
        this.answer = answer;
        folderNames = new ArrayList<>();
        isFavorite = false;
        this.userName = userName;
    }//constructor


    @Override
    public void modifyQuestion(String question){
        this.question = question;
    }//modifyQuestion


    @Override
    public void modifyAnswer(String answer){
        this.answer = answer;
    }//modifyAnswer


    @Override
    public String getQuestion(){
        return question;
    }//getQuestion


    @Override
    public String getAnswer(){
        return answer;
    }//getAnswer


    @Override
    public String getUserName(){
        return userName;
    }//getUserName


    @Override
    public void addFolderName(String folder){
        folderNames.add(folder);
    }//addFolderName


    @Override
    public List<String> getFolderNames(){
        return folderNames;
    }//getFolderName

}//Flashcard class

