package presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import flashcard.group5.application.R;
import interfaces.IFlashcard;
import logic.Account;
import logic.FlashcardLogic;
import objects.Flashcard;
import objects.User;

public class CardShelfActivity extends AppCompatActivity {

    //variables
    private Account userAccount;
    private User user;
    private String userName;
    private FlashcardLogic flashcardLogic;
    private TextView shelfCard;
    private ArrayList<Flashcard> uCards;
    private int cardNumber;
    private boolean isQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_shelf);

        userAccount = new Account(); //getting access to the User database
        userName = gettingUser();
        flashcardLogic = new FlashcardLogic();
        shelfCard = findViewById(R.id.shelfCard); //accessing card front text view
        isQuestion = true; //initially shows the question side
        uCards = new ArrayList<>();
        uCards.addAll(flashcardLogic.getAllCards());
        if(uCards.isEmpty()){
            uCards.add(new Flashcard("No other cards!", "You cant delete me!", "guest"));
        }//if no cards
        cardNumber = 0;
        shelfCard.setText(uCards.get(cardNumber).getQuestion()); //initially showing this card


    }//onCreate


    //gettingUser---setting up the current user
    private String gettingUser(){
        String userName = "guest";
        if(userAccount.getLoggedUser() != null){
            user = userAccount.getLoggedUser();
            userName = user.getUserName();
        }//if
        return userName;
    }//gettingUser


    //nextCard---when the next button is pressed
    public void nextCard(View view){
        if(cardNumber+1 < uCards.size()){
            cardNumber++;
            Flashcard fll = uCards.get(cardNumber); //get the next card associated with this user
            shelfCard.setText(fll.getQuestion());
        }//if has next card
    }//nextCard


    //prevCard---shows the previous card when the next button is pressed
    public void prevCard(View view){
        if(cardNumber > 0){
            cardNumber--;
            Flashcard fll = uCards.get(cardNumber); //get the next card associated with this user
            shelfCard.setText(fll.getQuestion());
        }//if has previous card
    }//prevCard


    //flipButton2---called when the flipped button is pressed
    public void flipButton2(View view){
        if(isQuestion){
            shelfCard.setText(uCards.get(cardNumber).getQuestion());
            isQuestion = false;
        }//if
        else{
            shelfCard.setText(uCards.get(cardNumber).getAnswer());
            isQuestion = true;
        }//else show answer
    }//filpButton2


    //deleteCard---this function will be implemented when the delete button is pressed
    public void deleteCard2(View view){
        flashcardLogic.deleteFlashcard(uCards.get(cardNumber));
        if(flashcardLogic.getFlashcard(uCards.get(cardNumber).getQuestion()) == null){
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


}//CardShelfActivity