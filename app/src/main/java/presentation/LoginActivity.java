package presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import flashcard.group5.application.R;
import logic.Account;


public class LoginActivity extends AppCompatActivity{

    private Account account;
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        account = new Account();
    }

    //will be called when the button "register" is clicked
    public void toRegister(View view){
        Intent intent = new Intent(this, ProfileCreationView.class);
        startActivity(intent);
    }

    //will be called when the button "login" is clicked
    public void toLogin(View view) {
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);

        //if we get a user back, that means this user trying to login is in the database.
        //This credential validation is based on username and the password to find a match.
        //If the user was not found in the database, we will generate a message.
        if(!account.login(usernameEditText.getText().toString(),passwordEditText.getText().toString()))
            showLoginFailed();
        else {
            updateUiWithUser();
        }
    }

    private void updateUiWithUser() {
        String welcome = getString(R.string.welcome) + usernameEditText.getText().toString();
        Intent intent = new Intent(this, OptionsActivity.class);
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        clearInput();
        startActivity(intent);
    }

    private void showLoginFailed() {
        String userNotFound = "Username or password is invalid.";
        Toast.makeText(getApplicationContext(), userNotFound, Toast.LENGTH_SHORT).show();
        clearInput();
    }

    private void clearInput() {
        usernameEditText.getText().clear();
        passwordEditText.getText().clear();
    }
}