package presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import flashcard.group5.application.R;

public class OptionsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        RelativeLayout createFlashCardWidget = findViewById(R.id.makeFlashCard); //this button will take you to the flashcard activity where users will be able to make a flashcard
        createFlashCardWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openFlashcardActivity(); }//onClick
        });
    }//onCreate

    public void openFlashcardActivity(){
        Intent intent_flashcard = new Intent(this, FlashcardActivity.class);
        startActivity(intent_flashcard);
    }//openFlashcardActivity
}//OptionsActivity
