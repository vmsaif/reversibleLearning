package interfaces;

import java.util.List;

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
    void addFolderName(String folder);

    //getFolderName---returns the list of folders this card is associated with
    List<String> getFolderNames();


}//IFlashcard ends
