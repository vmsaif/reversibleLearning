package presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.appcompat.widget.ButtonBarLayout;

import flashcard.group5.application.R;
import objects.Flashcard;
import android.widget.Button;

public class FlashcardActivity extends Activity {
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

//        Button button = (Button)findViewById(R.id.my_button);
//        button.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                makeFlashcard();
//            }//onClick
//        });
    }//onCreate


    public void makeFlashcard(){
        Flashcard fl = new Flashcard();
    }//makeFlashcard


}//FlashcardActivity class
