package presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import flashcard.group5.application.R;
import logic.Account;
import logic.AccountValidator;


public class LoginActivity extends AppCompatActivity{

    private Account account;
    private EditText usernameEditText;
    private EditText passwordEditText;
    public String message;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        account = new Account();  //this is hsqldb, we can inject another database - a fake one
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

        message = AccountValidator.validateUserName(usernameEditText.getText().toString());
        if (message != null) {
            showLoginFailed();
        } else {
            message = AccountValidator.validatePassword(passwordEditText.getText().toString());
                if (message != null)
                    showLoginFailed();
        }
            //if we get a user back, that means this user trying to login is in the database.
            //This credential validation is based on username and the password to find a match.
            //If the user was not found in the database, we will generate a message
            if(message == null) {

                if (!account.login(usernameEditText.getText().toString(), passwordEditText.getText().toString())) {
                    message = "Username or password is invalid.";
                    showLoginFailed();
                } else {
                    message = getString(R.string.welcome) + usernameEditText.getText().toString();
                    updateUiWithUser();
                }
            }
        }
    private void updateUiWithUser() {
        Intent intent = new Intent(this, OptionsActivity.class);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        clearInput();
        startActivity(intent);
    }

    private void showLoginFailed() {
        Log.d("test", "test " + message);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        clearInput();
    }

    private void clearInput() {
        usernameEditText.getText().clear();
        passwordEditText.getText().clear();
    }
}