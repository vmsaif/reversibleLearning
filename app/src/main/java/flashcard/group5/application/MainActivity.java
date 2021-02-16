package flashcard.group5.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import presentation.OptionsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this is button 1 option
        Button guestLogin = findViewById(R.id.button_guestLogin);
        guestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOptionsActivity();
            }//onClick
        });
    }//onCreate


    public void openOptionsActivity(){
        Intent intent_options = new Intent(getApplicationContext(), OptionsActivity.class);
        startActivity(intent_options);
    }//openOptionsActivity

}//MainActivity