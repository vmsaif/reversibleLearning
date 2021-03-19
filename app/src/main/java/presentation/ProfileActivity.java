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
import objects.User;

public class ProfileActivity extends AppCompatActivity {

    TextInputLayout userName, password;
    MaterialButton updateButton;
    Account account;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userName = findViewById(R.id.profileUserNameField);
        password = findViewById(R.id.profilePasswordField);
        updateButton = findViewById(R.id.profileUpdateButton);

        account = new Account();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    message = AccountValidator.validatePassword(password.toString());
                    message = AccountValidator.validateUserName(userName.toString());

                    if(message == null) {
                        if (account.changeUser(new User(userName.toString(), password.toString()))) {
                            Toast.makeText(getApplicationContext(), "Your information has been changed.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), OptionsActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Please login to change your information", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                    else Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();


                }
        });
    }
}
