package presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import flashcard.group5.application.R;
import logic.FlashcardLogic;
import objects.Flashcard;

public class CardviewActivity extends AppCompatActivity {

    //variables
    private AnimatorSet front_anim;
    private AnimatorSet back_anim;
    private boolean isFront;
    private FlashcardLogic flashcardLogic;
    private Flashcard flashcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);

        Bundle b = getIntent().getExtras();
        String question = b.getString("question");

        TextView card_front = findViewById(R.id.card_front); //accessing card front text view
        TextView card_back = findViewById(R.id.card_back); //accessing card back text view

        flashcardLogic = new FlashcardLogic();
        flashcard = flashcardLogic.getFlashcard(question);

        card_front.setText(flashcard.getQuestion()); //setting the question on card
        card_back.setText(flashcard.getAnswer()); //setting the answer on card

        isFront = true;
        float scale = getApplicationContext().getResources().getDisplayMetrics().density; //setting a scale variable to adjust view during animations
        card_front.setCameraDistance(8000*scale); //adjusting the camera distance
        card_back.setCameraDistance(8000*scale); //adjusting the camera distance

        front_anim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.front_anim); //setting  the front_anim variable to the animator resource file front_anim
        back_anim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.back_anim); //setting  the back_anim variable to the animator resource file back_anim
        Button flipButton = findViewById(R.id.flip_button);

        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                front_anim.setTarget(card_front);
                back_anim.setTarget(card_back);
                if(isFront){
                    front_anim.start();
                    back_anim.start();
                    isFront = false;
                }//if
                else{
                    back_anim.start();
                    front_anim.start();
                    isFront = true;
                }//else
            }//onClick
        });
    }//onCreate


    //deleteCard---this function will be implemented when the delete button is pressed
    public void deleteCard(View view){
        flashcardLogic.deleteFlashcard(flashcard);
        if(flashcardLogic.getFlashcard(flashcard.getQuestion()) == null){
            Toast.makeText(getBaseContext(), "Flashcard DELETED successfully", Toast.LENGTH_SHORT).show();//show a message telling the user that the flashcard is deleted
            openOptionsActivity();
        }//if
        else{
            Toast.makeText(getBaseContext(), "Flashcard NOT DELETED", Toast.LENGTH_SHORT).show();//show a message telling the user that the flashcard WAS NOT DELETED
        }//else

    }//deleteCard


    //openOptionsActivity---takes us back to the options page
    public void openOptionsActivity(){
        Intent intent_options = new Intent(this, OptionsActivity.class);
        startActivity(intent_options);
    }//openOptionsActivity


    //goHome---takes us to the options page
    public void goHome(View view){
        openOptionsActivity();
    }//goHome


}//Cardview class

