package presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import flashcard.group5.application.R;
import logic.FlashcardLogic;

public class FoldersActivity extends AppCompatActivity {

    //variables
    private ListView listView;
    private List folderList;
    private ArrayAdapter adapter;
    private FlashcardLogic flashcardLogic;
    private String folderName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folders);

        flashcardLogic = new FlashcardLogic();
        folderList = new ArrayList<>();
        folderList.addAll(flashcardLogic.getAllFolders());
        listView = findViewById(R.id.list_all_folders); //accessing the list view

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, folderList); //initializing the adapter
        listView.setAdapter(adapter); //passing the adapter to the xml file
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //this method helps us get the name of the folder that has been clicked
                folderName = folderList.get(position).toString(); //getting the folder name that is selected
                Toast.makeText(FoldersActivity.this, "Selected folder: " + folderName, Toast.LENGTH_SHORT).show();
                openFolderViewActivity();
            }//onItemClick
        });

    }//onCreate

    //openAddFolderActivity---opens add folder page
    public void openAddFolderActivity(View view){
        Intent intent_folder = new Intent(this, AddFolderActivity.class);
        startActivity(intent_folder);
    }//openAddFolderActivity


    //openFolderViewActivity---takes us to folder view activity
    private void openFolderViewActivity(){
        Intent intent_folder = new Intent(this, FolderViewActivity.class);
        Bundle b = new Bundle();
        b.putString("folderName",folderName);
        intent_folder.putExtras(b);
        startActivity(intent_folder);
    }//openFolderViewActivity


}//FoldersActivity