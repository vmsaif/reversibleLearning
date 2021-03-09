package flashcard.group5.application;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import data.hsqldb.UserDB;
import objects.User;
import presentation.LoginActivity;
import presentation.OptionsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button guestLogin;
    private Button login;
    private static UserDB userDB = new UserDB();

    public static UserDB getUserDB() { return userDB; }

    public static void updateUserDB(UserDB currUserDB) {
        userDB = currUserDB; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //these are 2 buttons
        guestLogin = findViewById(R.id.button_guestLogin);
        login = findViewById(R.id.button_login);

        guestLogin.setOnClickListener(this);
        login.setOnClickListener(this);
    }//onCreate

    @Override
    public void onClick(View view) {
        Intent intent = null;
        if (view.getId() == R.id.button_guestLogin) {
            intent = new Intent(getApplicationContext(), OptionsActivity.class);
        } else if(view.getId() == R.id.button_login) {
            intent = new Intent(MainActivity.this, LoginActivity.class);
        } else {
            //shouldn't have come here.....still in case
            Toast.makeText(this,"opps check buttons", Toast.LENGTH_SHORT).show();
        }
        if(intent != null) {
            startActivity(intent);
        }
    }
}//MainActivity