# Architecture
<p> Our Flashcard Study application is based on a three tier architecture. The tiers are Presentation tier, 
Logic Tier, Data Tier. In addition to that we also have a Objects package and an application package.
</p>

## Application
<p> Is the place which is used to launch the application.<br> 
This package includes:<br>
[MainActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/flashcard/group5/application/MainActivity.java) <br>
    * For now just shows the initial interface when the app launches, with the applications name on top.<br>
[Services](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/flashcard/group5/application/Services.java) <br>
    *The class that deals with the Persistance layer for FlashcardLogic and AccountValidator
</p>

## Presentation Tier
<p> The presentation tier is the user interface and communication layer of the application, where the 
end user interacts with the application. Its main purpose is to display information to and collect information from the user.<br>
This package includes: <br>
[CardviewActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/CardviewActivity.java) <br>
[FlashcardActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/FlashcardActivity.java) <br>
[LoginActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/LoginActivity.java) <br>
[ProfileActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/ProfileActivity.java) <br>
[ShelfcardActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/ShelfcardActivity.java) <br>
[OptionsActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/OptionsActivity.java) <br>
[ProfileCreationView](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/presentation/ProfileCreationView.java) <br>
</p>

## Logic Tier
<p> Information collected in the presentation tier is processed.<br>
This package includes:<br>
[FlashcardLogic](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/logic/FlashcardLogic.java) <br>
[AccountValidator](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/logic/AccountValidator.java) <br>
[Account](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/logic/Account.java) <br>
[LoggedUser](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/logic/LoggedUser.java) <br>
</p>

## Data Tier
<p> The Data Tier is where the information processed by the application is stored and managed.<br>
This package includes:<br>
HSQLDB:<br>
[FlashcardPersistenceHSQLDB](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/data/hsqldb/FlashcardPersistenceHSQLDB.java) <br>
[UserPersistenceHSQLDB](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/data/hsqldb/UserPersistenceHSQLDB.java) <br>
Stubs:<br>
[FlashcardPersistenceStub](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/data/stubs/FlashcardPersistenceStub.java) <br>
[UserPersistenceStub](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/data/stubs/UserPersistenceStub.java) <br>
</p>

## Objects
<p> This package contains the information of the various objects to be used by the rest of the project.<br>
This package includes:<br>
[Flashcard](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/objects/Flashcard.java)<br>
    *Representing the front and back side of a card.<br>
[User](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/main/java/objects/User.java)<br>
    *Represents the informations of the user
</p>

## Iteration 2 Diagram 

![architecture](Architecture_iteration2.png)
