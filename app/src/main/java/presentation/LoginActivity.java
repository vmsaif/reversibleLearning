package presentation;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import flashcard.group5.application.R;
import logic.ValidCredentials;
import objects.User;


public class LoginActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
    }


    //will be called when the button "register" is clicked
    public void toRegister(View view){
        Intent intent = new Intent(this, ProfileCreationView.class);
        startActivity(intent);
    }

    //will be called when the button "login" is clicked
    public void toLogin(View view) {
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);

        final User user = new User(usernameEditText.getText().toString(),
                passwordEditText.getText().toString());

        ValidCredentials validUser = new ValidCredentials(user);
        if(!validUser.userExists())
            showLoginFailed();
        else updateUiWithUser(user);
    }

    private void updateUiWithUser(User user) {
        String welcome = getString(R.string.welcome) + user.getUserName() ;
        Intent intent = new Intent(this, OptionsActivity.class);
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    private void showLoginFailed() {
        String userNotFound = "Username or password is invalid.";
        Toast.makeText(getApplicationContext(), userNotFound, Toast.LENGTH_SHORT).show();
    }



}