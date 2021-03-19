package presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import flashcard.group5.application.R;
import logic.Account;
import logic.AccountValidator;
import logic.LoggedUser;
import objects.User;

public class ProfileActivity extends AppCompatActivity {

    TextInputLayout userName, password;
    MaterialButton dashboardButton;
    User loggedInUser;
    Account account;
    String message1, message2;

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

//        account = new Account();

    }


//        updateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                    message1 = AccountValidator.validateUserName(userName.toString());
//                    message2 = AccountValidator.validatePassword(password.toString());
//
//                    if(message1 != null && message2 != null) {
//                        User newUser = new User(userName.toString(), password.toString());
//
//                        if (account.changeUser(newUser)) {
//                            loggedInUser = LoggedUser.getLoggedUser();
//                            Toast.makeText(getApplicationContext(), "Your information has been changed.", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
//                            startActivity(intent);
//
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Please login to change your information", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                            startActivity(intent);
//                        }
//                    } else {
//                        Toast.makeText(getApplicationContext(), message1, Toast.LENGTH_SHORT).show();
//                    }
//                }
//        });
//    }

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
