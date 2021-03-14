package presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import flashcard.group5.application.R;
import logic.FlashcardLogic;
import objects.Flashcard;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class FlashcardActivity extends Activity {

    //variables
    private String question = "";
    private String answer = "";
    private EditText inputQuestion;
    private EditText inputAnswer;
    private Button makeFlashcardButton;
    private FlashcardLogic flashLogic;
    private Flashcard flashcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

        //initialising the edit text fields
        inputQuestion = findViewById(R.id.editTextTextPersonName2);
        inputAnswer = findViewById(R.id.editTextTextPersonName3);

        makeFlashcardButton = (Button) findViewById(R.id.button_make_flashcard); //button to go to the animation showing the created card
        makeFlashcardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                question = inputQuestion.getText().toString(); //input from user on the question field
                answer = inputAnswer.getText().toString(); //input from user on the answer field
                if(!question.equals("") && !answer.equals("") && !question.trim().isEmpty() && !answer.trim().isEmpty()){
                    flashcard = new Flashcard(question, answer, "guest");
                    flashLogic = new FlashcardLogic(); //making a new flashcard logic object
//                    flashLogic.insertFlashcard(flashcard); //adding this flashcard to our database //error
                    Toast.makeText(getBaseContext(), "Flashcard created successfully", Toast.LENGTH_SHORT).show();//show a message telling the user that the flashcard creation has been successful
                    openCardviewActivity(flashLogic); //go to the GUI showing flashcard animation
                }//if fields are filled properly
                else{
                    Toast.makeText(getBaseContext(), "Fill in a question and answer to make a flashcard", Toast.LENGTH_SHORT).show();
                }//else user has not put in any text in question or answer field
            }//onClick
        });//set OnClickListener

    }//onCreate

//    public void openOptionsActivity(){
//        Intent intent_options = new Intent(this, OptionsActivity.class);
//        startActivity(intent_options);
//    }//openOptionsActivity

    public void openCardviewActivity(FlashcardLogic flashLogic){
        Intent intent_cardview = new Intent(this, CardviewActivity.class);
        startActivity(intent_cardview);
    }//openCardviewActivity

}//FlashcardActivity class
