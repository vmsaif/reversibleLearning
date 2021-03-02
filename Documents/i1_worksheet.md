Iteration 1 Worksheet
=====================

Adding a feature
-----------------
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/issues/1
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/issues/13
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/issues/28
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/issues/30
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/issues/33
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/issues/34
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/issues/37
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/issues/39

In order to make our flashcard learning app, we needed to make a java Flashcard object.
After some discussion, it was decided that a card would need two "Cardsides" in order
to properly handle the requirements we wanted of it, so a Cardside was added. Then, 
unit tests were created to make sure the Flashcard worked as we wanted it to.

Unfortunately, our original implememtation used a Cardside separate from the Flashcard
object. As we never intend for someone to make a Cardside object and it was not
an abstract object, it was brought to our attention that we were violating one of the
SOLID principles. In order to fix this issue, Cardside was moved into the Flashcard
object, fixing the SOLID violation.

Exceptional code
----------------

https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/app/src/test/java/objects/FlashcardListTest.java

From lines 26 to 39, we are checking to see if our Flashcard objects
are what we expect them to be with assertEquals(); If the method to
remove Flashcards is not working as expected, this will throw an
exception. because this is white box testing, we are using the direct
methods in FlashcardList for this testing.

Branching
----------

Our branching strategy is outlined at the bottom of our README:

https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/blob/master/README.md

Here is a screenshot of our branching strategy at work:

https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/tree/master/Documents/Document%20Pictures/i1BranchExample.png

You can see that a local branch is created, and then independantly of a different
local branch, updated by calling a pull from the master branch, and then a merge
into the master branch right after.

SOLID
-----

https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/24

Although not a traditional SOLID violation, we decided that the lack of architecture was enough
of a problem to warrant the issue we created, as it would help readability immensely.

Furthermore, due to the reliance on the superclass AppCompatActivity, the constructor for "user"
looks very 'smelly' due to the "init" method.

Agile Planning
--------------

Originally, we were planning on using the Git flow, but eventually we changed
to the GitHub instead. after working with GitLab and going through a few 
commits, we decided that we were more comfortable using a more lightweight
flow. We eventually came to this conclusion because we felt comfortable
creating local branches for new features, and relatively safe waiting for
group members to approve of merge requests from local branches straight to 
the master branch.
