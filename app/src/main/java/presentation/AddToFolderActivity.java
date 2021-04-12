package presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import flashcard.group5.application.R;
import logic.FlashcardLogic;
import objects.Flashcard;

public class AddToFolderActivity extends AppCompatActivity {

    //variables
    private ListView listView;
    private List folderList;
    private ArrayAdapter adapter;
    private FlashcardLogic flashcardLogic;
    private Flashcard flashcard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_folder);

        folderList = new ArrayList<>();
        flashcardLogic = new FlashcardLogic();
        flashcard = getFlashcard(); //gets us the current card we want to add to the folder

        folderList.addAll(flashcardLogic.getAllFolders());
        listView = findViewById(R.id.list_folders); //accessing the list view

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, folderList); //initializing the adapter
        listView.setAdapter(adapter); //passing the adapter to the xml file
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //this method helps us get the name of the folder that has been clicked
                String folderName = folderList.get(position).toString();
                flashcardLogic.insertCardToFolder(flashcard, folderName); //actually associating the card with this folder in our database
                Toast.makeText(AddToFolderActivity.this, "Added to folder: " + folderName, Toast.LENGTH_SHORT).show();
                openShelfcardActivity();
            }//onItemClick
        });

    }//onCreate


    //getFlashcard---gets the appropriate flashcard from the database so we can add it to folders
    private Flashcard getFlashcard(){
        Bundle b = getIntent().getExtras();
        String question = b.getString("question");
        return flashcardLogic.getFlashcard(question);
    }//getFlashcard


    //addFolder---adds a new folder to the data base
    public void openAddFolderActivity(View view){
        Intent intent_folder = new Intent(this, AddFolderActivity.class);
        startActivity(intent_folder);
    }//openAddFolderActivity


    //openShelfcardActivity---takes us to this activity
    private void openShelfcardActivity(){
        Intent intent_shelf = new Intent(this, ShelfCardActivity.class);
        startActivity(intent_shelf);
    }//openShelfcardActivity

}//AddToFolderActivity class