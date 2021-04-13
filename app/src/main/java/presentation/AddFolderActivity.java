package presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import flashcard.group5.application.R;
import logic.FlashcardLogic;

public class AddFolderActivity extends AppCompatActivity {

    //variables
    private FlashcardLogic flashcardLogic;
    private List folderList;
    private EditText folderName;
    private String name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_folder);

        flashcardLogic = new FlashcardLogic();
        folderList = new ArrayList<String>();
        folderList.addAll(flashcardLogic.getAllFolders()); //getting the list of all folders that exists
        folderName = findViewById(R.id.folder_name);
    }//onCreate


    //folderPresent---returns true if a folder with given name already exists
    private boolean folderPresent(String fName){
        boolean present = false;

        for(int i = 0; i < folderList.size(); i++){
            if(folderList.get(i).equals(fName)){
                present = true;
                break;
            }//if
        }//for i
        return present;
    }//folderPresent


    //createFolder---when the create folder button is clicked this method is called
    public void createFolder(View view){
        name = folderName.getText().toString();
        if(!name.equals("") && !name.trim().isEmpty()){
            if(!folderPresent(name.trim())){
                flashcardLogic.insertFolder(name); //inserting this folder in out database
                Toast.makeText(getBaseContext(), "Folder created successfully", Toast.LENGTH_SHORT).show();
                openOptionsActivity(); //go back to the card shelf
            }//if folder does not exists
            else{
                Toast.makeText(getBaseContext(), "This folder already exists!", Toast.LENGTH_SHORT).show();
            }//else folder exists
        }//if valid input
        else{
            Toast.makeText(getBaseContext(), "Input a valid folder name!", Toast.LENGTH_SHORT).show();
        }//else
    }//createFolder


    //openOptionsActivity---takes us to the options page activity
    private void openOptionsActivity(){
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }//openOptionsActivity


}//AddFolderActivity