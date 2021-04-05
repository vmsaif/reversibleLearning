package presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import flashcard.group5.application.R;
import logic.Account;
import logic.FlashcardLogic;
import objects.Flashcard;
import objects.User;

import android.widget.EditText;
import android.widget.Toast;


public class FlashcardActivity extends Activity {

    //variables
    private String question = "";
    private String answer = "";
    private EditText inputQuestion;
    private EditText inputAnswer;
    private FlashcardLogic flashLogic;
    private Account account;
    private User user;
    private String userName;
    private Flashcard flashcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        flashLogic = new FlashcardLogic(); //making a new flashcard logic object
        account = new Account(); //making a new account object to get the current user
        if(account.getLoggedUser() != null) {
            user = account.getLoggedUser();
            userName = user.getUserName();
        }//if
        else{
            userName = "guest";
        }//else
        //initialising the edit text fields
        inputQuestion = findViewById(R.id.editTextTextPersonName2);
        inputAnswer = findViewById(R.id.editTextTextPersonName3);
    }//onCreate


    public void openCardviewActivity(){
        Intent intent_cardview = new Intent(this, CardviewActivity.class);
        Bundle b = new Bundle();
        b.putString("question",question);
        intent_cardview.putExtras(b);
        startActivity(intent_cardview);
    }//openCardviewActivity


    //makeCard---will be called when button 'Make Flashcard' is clicked
    public void makeCard(View view){
        question = inputQuestion.getText().toString(); //input from user on the question field
        answer = inputAnswer.getText().toString(); //input from user on the answer field
        if(!question.equals("") && !answer.equals("") && !question.trim().isEmpty() && !answer.trim().isEmpty()){
            flashcard = new Flashcard(question, answer, userName);
            flashLogic.insertFlashcard(flashcard); //adding this flashcard to our database
            Toast.makeText(getBaseContext(), "Flashcard created successfully", Toast.LENGTH_SHORT).show();//show a message telling the user that the flashcard creation has been successful
            openCardviewActivity(); //go to the GUI showing flashcard animation
        }//if fields are filled properly
        else{
            Toast.makeText(getBaseContext(), "Fill in a question and answer to make a flashcard", Toast.LENGTH_SHORT).show();
        }//else user has not put in any text in question or answer field
    }//makeCard

}//FlashcardActivity class
