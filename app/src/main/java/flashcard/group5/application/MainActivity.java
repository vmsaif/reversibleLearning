package flashcard.group5.application;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import data.hsqldb.UserDB;
import objects.User;
import presentation.LoginActivity;
import presentation.OptionsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button guestLogin;
    private Button login;
    private static UserDB userDB = new UserDB();
    private String tag = "MainActivity";

    //public static UserDB getUserDB() { return userDB; }

    //public static void updateUserDB(UserDB currUserDB) {
      //  userDB = currUserDB; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseToDevice();
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

    private void databaseToDevice() {
        final String DB_PATH = "db";
        Log.d(tag, "In the databaseToDevice" );
        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Services.setDBPathName(dataDirectory.toString() + "/" + Services.getDBPathName());
            Log.d(tag, "path name is " + Services.getDBPathName());

        } catch (final IOException ioe) {
            Log.w(tag, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();
        Log.d(tag, "In the copyAssetsToDirectory" );
        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

}//MainActivity