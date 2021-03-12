package objects;
import interfaces.IFlashcard;

public class Flashcard implements IFlashcard{

    //variables
    private String question;
    private String answer;
    private String userName;
    private String folderName = "";


    //constructor---will create a Flashcard object that has two sides (CardSide) front and back
    public Flashcard(String question, String answer, String userName){
        this.question = question;
        this.answer = answer;
        if(userName.equals("")  || userName.toLowerCase().equals("guest")){  //if it does'nt belong to any account
            this.userName = "Guest";
        }//if this flashcard is not related to any user
        else{
            this.userName = userName;
        }//else
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
    public void setFolderName(String folder){
        folderName = folder;
    }//setFolderName


    @Override
    public String getFolderName(){
        return folderName;
    }//getFolderName
    

}//Flashcard class

