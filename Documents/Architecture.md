# Architecture
<p> Our Flashcard Study application is based on a three tier architecture. The tiers are Presentation tier, 
Logic Tier, Data Tier. In addition to that we also have a Objects package and an application package.
</p>

## Application
<p> Is the place which is used to launch the application.<br> 
This package includes:<br>
[MainActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/flashcard/group5/application/MainActivity.java) <br>
    * For now just shows the initial interface when the app launches, with the applications name on top.<br>
</p>

## Presentation Tier
<p> The presentation tier is the user interface and communication layer of the application, where the 
end user interacts with the application. Its main purpose is to display information to and collect information from the user.<br>
This package includes: <br>
[CardviewActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/CardviewActivity.java) <br>
[FlashcardActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/FlashcardActivity.java) <br>
[LoggedInUserView](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/LoggedInUserView.java) <br>
[LoginActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/LoginActivity.java) <br>
[LoginFormState](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/LoginFormState.java) <br>
[LoginResult](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/LoginResult.java) <br> 
[LoginViewModel](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/LoginViewModel.java) <br>
[LoginViewModelFactory](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/LoginViewModelFactory.java) <br>
[OptionsActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/OptionsActivity.java) <br>
[ProfileCreationView](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/ProfileCreationView.java) <br>
</p>

## Logic Tier
<p> Information collected in the presentation tier is processed.<br>
This package includes:<br>
[FlashcardLogic](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/logic/FlashcardLogic.java) <br>
</p>

## Data Tier
<p> The Data Tier is where the information processed by the application is stored and managed.<br>
This package includes:<br>
[Flashcards_list](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/data/Flashcards_list.java) <br>
[LoginDataSource](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/data/LoginDataSource.java) <br>
[LoginRepository](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/data/LoginRepository.java) <br>
[Result](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/data/Result.java) <br>
[UserDB](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/data/UserDB.java) <br>
</p>

## Objects
<p> This package contains the information of the various objects to be used by the rest of the project.<br>
This package includes:<br>
[CardSide](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/objects/CardSide.java)<br>
    * Represents a side of a flashcard. Contains question/answer depending on what side it is.<br>
[Flashcard](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/objects/Flashcard.java)<br>
    *Will hold 2 CardSide objects representing the front and back side of a card.<br>
[FlashcardList](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/objects/FlashcardList.java)<br>
    *List of Flashcards <br>
[User](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/objects/User.java)<br>
    *Represents the informations of the user
</p>

## Iteration 1 Diagram 

![architecture](Architecture_diagram-2.png)
