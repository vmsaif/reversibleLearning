package presentation;
import data.LoginDataSource;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                    UserDB userDB = MainActivity.getUserDB();
                    User currUser = new User(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                    userDB.addUser(currUser);
                    MainActivity.updateUserDB(userDB);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });


        }

}
