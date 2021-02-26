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

import flashcard.group5.application.R;
import logic.FlashcardLogic;

public class CardviewActivity extends AppCompatActivity {

    //variables
    private ObjectAnimator anime_1;
    private ObjectAnimator anime_2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);

        TextView front_card = (TextView) findViewById(R.id.card_front);

        front_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anime_1 = ObjectAnimator.ofFloat(front_card, "scaleX", 1f, 0f);//first animation will shrink to reveal the back side
                anime_2 = ObjectAnimator.ofFloat(front_card, "scaleX", 0f, 1f);//reverse of the previous line
                anime_1.setInterpolator(new DecelerateInterpolator());//reducing speed of animation at the start while flipping
                anime_2.setInterpolator(new AccelerateInterpolator());//bringing the speed back ti normal

                anime_1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        front_card.setText("Answer"); //placeholder text reveals the answer...real implementation will be done once actual database is implemented
                        anime_2.start();
                    }
                });//AnimatorListenerAdapter
                anime_1.start();
            }//onClick
        });

    }//onCreate
}//Cardview class

