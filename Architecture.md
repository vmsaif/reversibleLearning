#Architecture
<p>Our Flashcard Study application is based on a three tier architecture. The tiers are Presentation tier, 
Logic Tier, Data Tier. In addition to that we also have a Objects package and an application package.
</p>

##Application
<p>Is the place which is used to launch the application. 
This package includes:
1. MainActivity: 
    * For now just shows the initial interface when the app launches, with the applications name on top.
</p>

##Presentation Tier
<p> The presentation tier is the user interface and communication layer of the application, where the 
end user interacts with the application. Its main purpose is to display information to and collect information from the user.
This package includes:

</p>

##Logic Tier
<p>information collected in the presentation tier is processed.
This package includes:

</p>

##Data Tier
<p>The Data Tier is where the information processed by the application is stored and managed.
This package includes:
</p>

##Objects
<p>This package contains the information of the various objects to be used by the rest of the project.
This package includes:
1.CardSide class
    *Represents a side of a flashcard. Contains question/answer depending on what side it is.
2.Flsahcard class:
    *Will hold 2 CardSide objects representing the front and back side of a card.
</p>