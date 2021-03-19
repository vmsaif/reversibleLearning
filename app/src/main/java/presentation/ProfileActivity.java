package presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import flashcard.group5.application.R;
import logic.Account;

public class ProfileActivity extends AppCompatActivity {

    TextInputLayout userName, password;
    MaterialButton updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userName = findViewById(R.id.profileUserNameField);
        password = findViewById(R.id.profilePasswordField);
        updateButton = findViewById(R.id.profileUpdateButton);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // button things
            }
        });
    }
}
