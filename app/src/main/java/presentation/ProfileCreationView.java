package presentation;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import flashcard.group5.application.R;

public class ProfileCreationView extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            Log.d("test", "myTest");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
        }
}
