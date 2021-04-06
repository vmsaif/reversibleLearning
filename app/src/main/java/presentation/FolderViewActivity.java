package presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import flashcard.group5.application.R;
import logic.FlashcardLogic;
import objects.Flashcard;

public class FolderViewActivity extends AppCompatActivity {

    //variables
    private FlashcardLogic flashcardLogic;
    private String folderName;
    private TextView title;
    private TextView shelfCard;
    private ArrayList<Flashcard> uCards;
    private int cardNumber;
    private boolean isQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_view);

        flashcardLogic = new FlashcardLogic();
        Bundle b = getIntent().getExtras();
        folderName = b.getString("folderName"); //getting the folder name from the previous activity

        title = findViewById(R.id.folder_name);
        title.setText(folderName); //setting the title to the name of the selected folder

        shelfCard = findViewById(R.id.shelfCard2); //accessing card front text view
        isQuestion = true; //initially shows the question side
        uCards = new ArrayList<>();
        uCards.addAll(flashcardLogic.getFolderCards(folderName)); //getting all the cards in this folder

        if(uCards.isEmpty()){
            uCards.add(new Flashcard("No other cards!", "You cant delete me!", "guest"));
        }//if no cards
        cardNumber = 0;
        shelfCard.setText(uCards.get(cardNumber).getQuestion()); //initially showing this card
    }//onCreate


    //nextCard---when the next button is pressed
    public void nextCard2(View view){
        if(cardNumber+1 < uCards.size()){
            cardNumber++;
            Flashcard fll = uCards.get(cardNumber); //get the next card associated with this user
            shelfCard.setText(fll.getQuestion());
        }//if has next card
    }//nextCard


    //prevCard---shows the previous card when the next button is pressed
    public void prevCard2(View view){
        if(cardNumber > 0){
            cardNumber--;
            Flashcard fll = uCards.get(cardNumber); //get the next card associated with this user
            shelfCard.setText(fll.getQuestion());
        }//if has previous card
    }//prevCard


    //flipButton2---called when the flipped button is pressed
    public void flipButton3(View view){
        if(isQuestion){
            shelfCard.setText(uCards.get(cardNumber).getQuestion());
            isQuestion = false;
        }//if
        else{
            shelfCard.setText(uCards.get(cardNumber).getAnswer());
            isQuestion = true;
        }//else show answer
    }//filpButton2


    //delFolder---will be called when delete folder button is pressed
    public void delFolder(View view){
        flashcardLogic.deleteFolder(folderName);
        Toast.makeText(FolderViewActivity.this, folderName + " folder deleted successfully!", Toast.LENGTH_SHORT).show();
        openOptionsActivity(null);
    }//delFolder


    //openOptionsActivity---takes us back to the options page
    public void openOptionsActivity(View view){
        Intent intent_options = new Intent(this, OptionsActivity.class);
        startActivity(intent_options);
    }//openOptionsActivity


    //removeCard---removes the current card from this folder
    public void removeCard(View view){
        flashcardLogic.removeCardFromFolder(uCards.get(cardNumber), folderName);
        Toast.makeText(FolderViewActivity.this, "Card removed successfully!", Toast.LENGTH_SHORT).show();
        openOptionsActivity(null);
    }//removeCard

}//FolderViewActivity