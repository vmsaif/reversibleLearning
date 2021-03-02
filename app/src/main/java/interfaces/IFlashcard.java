package interfaces;

public interface IFlashcard {

    //modifyQuestion---can edit the question
    void modifyQuestion(String question);

    //modifyAnswer---can edit the answers
    void modifyAnswer(String answer);

    //returnQuestion---return the question string
    String returnQuestion();

    //returnAnswer---return the answer string
    String returnAnswer();

}//IFlashcard ends
