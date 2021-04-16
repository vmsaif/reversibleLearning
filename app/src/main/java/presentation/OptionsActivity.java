package presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

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

        RelativeLayout profileWidget = findViewById(R.id.profileActivity);
        profileWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfileActivity();
            }
        });

        RelativeLayout cardShelfWidget = findViewById(R.id.cardShelf);
        cardShelfWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openShelfcardActivity();
            }
        });
        RelativeLayout folderWidget = findViewById(R.id.AllFolders);
        folderWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFoldersActivity();
            }
        });

    }//onCreate


    private void openProfileActivity() {
        Intent intent_flashcard = new Intent(this, ProfileActivity.class);
        startActivity(intent_flashcard);
    }//openProfileActivity


    private void openFlashcardActivity(){
        Intent intent_flashcard = new Intent(this, FlashcardActivity.class);
        startActivity(intent_flashcard);
    }//openFlashcardActivity


    //openShelfcardActivity---goes to the activity where a the card shelf is
    private void openShelfcardActivity(){
        Intent intent_flashcard = new Intent(this, ShelfCardActivity.class);
        startActivity(intent_flashcard);
    }//openopenShelfcardActivity


    //openFoldersActivity---goes to the activity where a the folders are
    public void openFoldersActivity(){
        Intent intent_folders = new Intent(this, FoldersActivity.class);
        startActivity(intent_folders);
    }//openopenFoldersActivity

}//OptionsActivity
