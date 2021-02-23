package presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import flashcard.group5.application.R;

public class CardviewActivity extends AppCompatActivity {

    //variables
    private AnimatorSet front_anim;
    private AnimatorSet back_anim;
    private boolean isFront = true;
    private Button flipButton;
    private TextView front_t;
    private TextView back_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;

        front_t = (TextView) findViewById(R.id.card_front);
        back_t = (TextView) findViewById(R.id.card_back);
        front_t.setCameraDistance(8000 * scale);
        back_t.setCameraDistance(8000 * scale);

        front_anim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.front_animator);
        back_anim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.back_animator);

        flipButton = (Button) findViewById(R.id.button_flip);
        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFront){
                    front_anim.setTarget(front_t);
                    back_anim.setTarget(back_t);
                    front_anim.start();
                    back_anim.start();
                    boolean isFront = false;
                }//if
                else {
                    front_anim.setTarget(front_t);
                    back_anim.setTarget(back_t);
                    back_anim.start();
                    front_anim.start();
                    boolean isFront = true;
                }//else
            }//onClick
        });

    }//onCreate
}//Cardview class

