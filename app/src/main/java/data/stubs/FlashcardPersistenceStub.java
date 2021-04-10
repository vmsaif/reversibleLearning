package data.stubs;

import java.util.ArrayList;
import java.util.List;

import data.FlashcardPersistence;
import objects.Flashcard;

public class FlashcardPersistenceStub implements FlashcardPersistence {

    // List of Flashcards
    private List<Flashcard> flashcards;
    // List of Folders
    private List<String> folders;
    // List of Folders to Flashcards
    private List<List<String>> card_folders;


    //constructor
    public FlashcardPersistenceStub(){
        this.flashcards = new ArrayList<>();
        flashcards.add(new Flashcard("What is the answer of this question?", "answer1000", "guest3000"));
        flashcards.add(new Flashcard("This is a question", "This is an answer", "mike"));
        flashcards.add(new Flashcard("question", "answer", "group5"));
        Flashcard fCard = new Flashcard("question1", "answer1", "user1");
        Flashcard fCard2 = new Flashcard("question2", "answer2", "user2");
        Flashcard fCard3 = new Flashcard("question3", "answer3", "user3");
        fCard.addFolderName("folder1");
        fCard.addFolderName("folder2");
        fCard.addFolderName("folder13");
        flashcards.add(fCard);
    }//constructor


    @Override
    public void insertFlashcard(Flashcard givenFlashcard){
        flashcards.add(givenFlashcard);
    }//insertFlashcard


    @Override
    public void insertFolder(String folder) {
        // add the folder to the folder names list
        folders.add(folder);
        // add the folder to the card_folders
        // name the very first line of the list's list the name of the folder
        ArrayList<String> insideFolder = new ArrayList<>();
        insideFolder.add(folder);
        // add the inside folder to the card_folders
        card_folders.add(insideFolder);
    }//insertFolder

    @Override
    public List<String> getFlashcardFolders(Flashcard flashcard) {
        Flashcard card = null;
        int search = searchFlashcard(flashcard);
        if(search != -1){
            card = getFlashcardSequential().get(search);
        }
        return card == null ? null : card.getFolderNames();
    }//getFlashcardFolders


    @Override
    public List<Flashcard> getUserCards(String userName) {
        ArrayList<Flashcard> fCard = new ArrayList<>();
        for(int i=0; i<flashcards.size(); i++){
            if(flashcards.get(i).getUserName().equals(userName)){
                fCard.add(flashcards.get(i));
            }//if
        }//for i
        return fCard;
    }

    @Override
    public List<Flashcard> getAllFlashcards() {
        return flashcards;
    }//getAllFlashcards


    @Override
    public List<String> getAllFolders() {
        ArrayList<String> folder = new ArrayList<>();
        for(int i=0; i<flashcards.size(); i++){
            folder.addAll(flashcards.get(i).getFolderNames());
        }//for i
        return folder;
    }

    @Override
    public void insertCardToFolder(Flashcard flashcard, String folder) {
        int folderLocation = findFolder(folder);
        // if the folder name doesn't exists, add a new List
        if(folderLocation == -1){
            // name the very first line of the list's list the name of the folder
            ArrayList<String> insideFolder = new ArrayList<>();
            insideFolder.add(folder);
            // add the question of the flashcard to the inside folder list
            insideFolder.add(flashcard.getQuestion());
            // add the inside folder to the card_folders
            card_folders.add(insideFolder);
        }
        else{
            // if the folder name exists
            List<String> localFolder = card_folders.get(folderLocation);
            localFolder.add(flashcard.getQuestion());
            card_folders.set(folderLocation, localFolder);
        }

        // add the folder to the flashcard
        int search = searchFlashcard(flashcard);
        if(search != -1){
            Flashcard temp = flashcards.get(search);
            temp.addFolderName(folder);
            flashcards.set(search, temp);
        }
    }

    private int findFolder(String folder){
        int result = -1;
        for(int i = 0; i < card_folders.size(); i++){
            if(card_folders.get(i).get(0).equals(folder)){
                result = i;
            }
        }
        return result;
    }

    @Override
    public void deleteFolder(String folderName) {
        // remove folder from folders list
        folders.remove(folderName);
        // remove folder on the card_folder list
        int folderLocation = findFolder(folderName);
        // before deleting the folder, copy to local list for removing the folders on the flashcard
        List<String> localFolder = card_folders.get(folderLocation);
        card_folders.remove(folderLocation);
        // remove folder on each of the flashcard
        for(int i = 1; i < localFolder.size(); i++){
            int flashcardLocation = searchQuestion(localFolder.get(i));
            // remove the folder name on each flashcard on the list
            Flashcard localFlashcard = flashcards.get(flashcardLocation);
            localFlashcard.getFolderNames().remove(folderName);
            flashcards.set(flashcardLocation, localFlashcard);
        }
    }

    @Override
    public List<Flashcard> getFolderCards(String folderName) {
        // returns all the cards in a folder
        int folderLocation = findFolder(folderName);
        // put all the cards into a list
        List<Flashcard> localList = new ArrayList<>();
        List<String> questionList = card_folders.get(folderLocation);
        for(int i = 1; i < questionList.size(); i++){
            int questionLocation = searchQuestion(questionList.get(i));
            localList.add(flashcards.get(questionLocation));
        }
        return localList;
    }

    @Override
    public void removeCardFromFolder(Flashcard flashcard, String folder) {
        // remove card from the card_folder list
        int folderLocation = findFolder(folder);
        List<String> localFolder = card_folders.get(folderLocation);
        localFolder.remove(flashcard.getQuestion());
        card_folders.set(folderLocation, localFolder);
        // remove the folder on the card
        int flashcardLocation = searchQuestion(flashcard.getQuestion());
        Flashcard localFlashcard = flashcards.get(flashcardLocation);
        localFlashcard.getFolderNames().remove(folder);
        flashcards.set(flashcardLocation, localFlashcard);
    }


    private int searchFlashcard(Flashcard flashcard){
        int result = -1;
        for(int i = 0; i < flashcards.size(); i++){
            if(flashcards.get(i).getQuestion().equals(flashcard.getQuestion()) &&
                    flashcards.get(i).getAnswer().equals(flashcard.getAnswer()) &&
                    flashcards.get(i).getUserName().equals(flashcard.getUserName())){
                result = i;
            }
        }
        return result;
    }

    private int searchQuestion(String question){
        int result = -1;
        for(int i = 0; i < flashcards.size(); i++){
            if(flashcards.get(i).getQuestion().equals(question)){
                result = i;
            }
        }
        return result;
    }


    @Override
    public List<Flashcard> getFlashcardSequential(){
        return flashcards;
    }//getFlashcardSequential


    @Override
    public void deleteFlashcard(Flashcard currentFlashcard) {
        flashcards.remove(currentFlashcard);
    }//deleteFlashCard

    @Override
    public Flashcard getFlashcard(String question) {
        Flashcard fCard = null;
        for(int i=0; i<flashcards.size(); i++){
            if(flashcards.get(i).getQuestion().equals(question)){
                fCard = flashcards.get(i);
            }//if
        }//for i
        return fCard;
    }//getFlashCard


}
