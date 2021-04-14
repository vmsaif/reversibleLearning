package presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import data.hsqldb.FlashcardPersistenceHSQLDB;
import flashcard.group5.application.R;
import logic.Account;
import logic.FlashcardLogic;
import logic.LoggedUser;
import objects.Flashcard;
import objects.User;

public class ProfileActivity extends AppCompatActivity {

    //variables
    private TextInputLayout userName, password;
    private MaterialButton dashboardButton;
    private User loggedInUser;
    private String userNameFromDB;
    private String passwordFromDB;
    private FlashcardLogic flashcardLogic;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        flashcardLogic = new FlashcardLogic();
        account = new Account();

        userName = findViewById(R.id.profileUserNameField);
        password = findViewById(R.id.profilePasswordField);
        dashboardButton = findViewById(R.id.profileUpdateButton);

        loggedInUser = LoggedUser.getLoggedUser();

        showAllData();

        TextView cardCount = findViewById(R.id.cardsCountInt);
        cardCount.setText(getNumberOfUserCards());
    }//onCreate

    private void showAllData() {

        if(loggedInUser != null){
            userNameFromDB = loggedInUser.getUserName();
            passwordFromDB = loggedInUser.getPassword();

            userName.getEditText().setText(userNameFromDB);
            password.getEditText().setText(passwordFromDB);

        }//if
    }//showAllData

    public void updateUser(View view){
        userName = findViewById(R.id.profileUserNameField);
        password = findViewById(R.id.profilePasswordField);
        String newUserName = userName.getEditText().getText().toString();
        String newPassword = password.getEditText().getText().toString();
        account.changeUser(new User(newUserName,newPassword));
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }

    //getNumberOfUserCards---returns how many cards a user has
    private String getNumberOfUserCards(){
        int totalCards = 0;
        List<Flashcard> allUserCards = new ArrayList<>();
        allUserCards = flashcardLogic.getUsersCards(userNameFromDB);
        if(!allUserCards.isEmpty()){
            totalCards = allUserCards.size();
        }//if
        return totalCards + "";
    }//getNumberOfUserCards


    public void openOptionsActivity(View view){
        Intent intent_options = new Intent(this, OptionsActivity.class);
        startActivity(intent_options);
    }//openOptionsActivity

}//ProfileActivity class
