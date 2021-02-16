# Architecture
<p> Our Flashcard Study application is based on a three tier architecture. The tiers are Presentation tier, 
Logic Tier, Data Tier. In addition to that we also have a Objects package and an application package.
</p>

## Application
<p> Is the place which is used to launch the application.<br> 
This package includes:<br>
1. MainActivity: <br>
    * For now just shows the initial interface when the app launches, with the applications name on top.<br>
</p>

## Presentation Tier
<p> The presentation tier is the user interface and communication layer of the application, where the 
end user interacts with the application. Its main purpose is to display information to and collect information from the user.<br>
This package includes:<br>

</p>

## Logic Tier
<p> Information collected in the presentation tier is processed.<br>
This package includes:<br>

</p>

## Data Tier
<p> The Data Tier is where the information processed by the application is stored and managed.<br>
This package includes:<br>
</p>

## Objects
<p> This package contains the information of the various objects to be used by the rest of the project.<br>
This package includes:<br>
1. CardSide class<br>
    * Represents a side of a flashcard. Contains question/answer depending on what side it is.<br>
2. Flsahcard class:<br>
    *Will hold 2 CardSide objects representing the front and back side of a card.<br>
</p>