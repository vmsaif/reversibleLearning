package interfaces;

public interface IFlashcard {

    //modifyQuestion---can edit the question
    void modifyQuestion(String question);

    //modifyAnswer---can edit the answers
    void modifyAnswer(String answer);

    //getQuestion---return the question string
    String getQuestion();

    //getAnswer---return the answer string
    String getAnswer();

    //getUserName---returns the userName
    String getUserName();

    //setFolderName---sets folderName if this flashcard is placed in a folder
    void setFolderName(String folder);

    //getFolderName---returns the name of the folder this card is associated with
    String getFolderName();

}//IFlashcard ends
