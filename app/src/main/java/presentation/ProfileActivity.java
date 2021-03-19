package presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import flashcard.group5.application.R;
import logic.LoggedUser;
import objects.User;

public class ProfileActivity extends AppCompatActivity {

    TextInputLayout userName, password;
    MaterialButton dashboardButton;
    User loggedInUser;
    
    String userNameFromDB;
    String passwordFromDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userName = findViewById(R.id.profileUserNameField);
        password = findViewById(R.id.profilePasswordField);
        dashboardButton = findViewById(R.id.profileUpdateButton);

        loggedInUser = LoggedUser.getLoggedUser();

        showAllData();
    }

    private void showAllData() {

        if(loggedInUser != null){
            userNameFromDB = loggedInUser.getUserName();
            passwordFromDB = loggedInUser.getPassword();

            userName.getEditText().setText(userNameFromDB);
            password.getEditText().setText(passwordFromDB);

        }
    }
    public void openOptionsActivity(View view){
        Intent intent_options = new Intent(this, OptionsActivity.class);
        startActivity(intent_options);
    }//openOptionsActivity
}
