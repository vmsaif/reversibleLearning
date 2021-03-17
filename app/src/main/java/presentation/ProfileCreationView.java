package presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import data.hsqldb.UserDB;
import flashcard.group5.application.MainActivity;
import flashcard.group5.application.R;
import logic.Account;
import objects.User;

public class ProfileCreationView extends AppCompatActivity {

    //account object
    private Account newAccount;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            final EditText usernameEditText = findViewById(R.id.username);
            final EditText passwordEditText = findViewById(R.id.password);
            final Button register = findViewById(R.id.button_register);

            newAccount = new Account();

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //try to add new account. If success navigate the user to the loginActivity so they can login.
                    //Otherwise, show an error message
                    if (newAccount.addNewAccount(usernameEditText.getText().toString(), passwordEditText.getText().toString())) {
                        usernameEditText.getText().clear();
                        passwordEditText.getText().clear();
                        Intent intent = new Intent(ProfileCreationView.this, LoginActivity.class);
                        startActivity(intent);
                    } else showRegisterFailed();
                }
            });
        }

    private void showRegisterFailed() {
        String userNotFound = "Username exist. Please use different user name.";
        Toast.makeText(getApplicationContext(), userNotFound, Toast.LENGTH_SHORT).show();
    }
}
