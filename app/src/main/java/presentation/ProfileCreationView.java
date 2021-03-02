package presentation;
import data.LoginDataSource;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import data.LoginDataSource;
import data.UserDB;
import flashcard.group5.application.MainActivity;
import flashcard.group5.application.R;
import objects.User;

public class ProfileCreationView extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            final EditText usernameEditText = findViewById(R.id.username);
            final EditText passwordEditText = findViewById(R.id.password);
            final Button register = findViewById(R.id.button_register);

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!isUserNameValid(usernameEditText.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a valid user name", Toast.LENGTH_SHORT).show();

                    else if(!isPasswordValid(passwordEditText.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a valid password. Password must be > 5.", Toast.LENGTH_SHORT).show();

                    else {
                        UserDB userDB = MainActivity.getUserDB();
                        User currUser = new User(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                        userDB.addUser(currUser);
                        MainActivity.updateUserDB(userDB);
                        Intent intent = new Intent(ProfileCreationView.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            });

        }
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

}
