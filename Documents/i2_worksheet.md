Iteration 1 Worksheet
=====================

Paying off technical debt
-----------------
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/commit/195f36716141cb1ceee49d9f9141b65b316ae413#c9cd8542c8a3262cf8164ce19e3968bc96cfa59c_9_21

In this example, we needed to implement our actual database, as Cardview Activity and Flashcard Activity were not actually passing flashcards between each other (dummy methods).\
This was prudent deliberate technical debt. We knew that we would be implementing a database later on in the project, so we put in a placeholder at the time, and then we needed to go back in later and pay off the technical debt.


https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/commit/541f4797f6bde4d39340c27133399b01f7f30cd5?merge_request_iid=21#e2cc4ad0f72f6c40792e58d1e873bac68b78f3d2_1_0

This is an example of us needing to pay off a debt of planning. When implementing our database, it ended up getting tied up in interface, presentation and data layers. This was unintended reckless debt.\
We did not intend on having such a highly coupled database, and as a result, we needed to refactor a lot of the code to decouple our database from all the layers of our project. 
That is why I am linking a line at the very start of the commit, it isn't really on a specific line that we pay off the debt in this commit, but rather the whole commit is refactoring the code out to pay off our technical debt.


SOLID
-----

https://code.cs.umanitoba.ca/3350-winter-2021-a03/group5app-comp3350-a03-group5/-/issues/44

The SOLID violation is a logic class breaking the single-responsibility priinciple, as the CalendarLogic class
is doing all the calendar logic and logic for events as well, lowering cohesion.


Retrospective
----------------

Our retrospective did seriously change the way we are doing testing. Instead of the way we had done testing previously, where we assigned one person to write tests for our various classes and interfaces, we decided to all write tests for our own code and send them to the person who was originally writing tests.\
By doing this, it makes it easier for our tester to create tests, and also helps prevent issues where changes in the codebase are not reflected in our testing framework, leading to tests that do not run after changes.\
However, this change is not reflected on gitlab, because the tests written and sent to the individual in charge of the tests were not logged over gitlab.\
(We use Discord for team communication)


Design patterns
----------

https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/commit/392e926bd2e5da2295980502d3bbdc625fa67ab8#397b372f7c253c641fa5072eb553899d080a9a16_24_24
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/commit/204f80cd8538cb13009e3d375570a8e5de60b507#86739463c1d652089ca4c62a4558d35ffd3c225d_21_21

We use the singleton design pattern for our database. This is to prevent multiple database instances, which would be undesirable for storing relatively small amounts of data.\
Here we create a final varuable for the db path, so we can insure we are always in the same database.


Iteration 1 Feedback fixes
--------------

https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/issues/73

There were 3 parts to the issue, Questions and Answers not being displayed properly after creating a flashcard, inactive buttons on the UI and improper validation on the user information. 
These were flagged for the following reasons: Questions and Answers because they are an integral part to the application, whose functionality was not finished. Inactive UI buttons because they 
Confuse the user and detract from the experience (and are also unfinished features) and user validation because we had an issue in the code for validating users, which is a danger for security.

https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/commit/c584e6ce Is where we refactor the code to remove the inactive buttons. We had them set up for future features we had planned, but as they are not finished yet, we changed them to completed features.\
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/commit/0dc332457ad7494b1ac650c0529038e1b783ee2a Fixes the user validation by storing whether the user input lower or uppercase latters.\
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/commit/392e926bd2e5da2295980502d3bbdc625fa67ab8 Fixes the question and answer not showing the input valuse by linking the question and answer fields to the database we set up. You are not intended to flip back to a question after seeing the answer, so we removed the flip button on the answer presentation.

Explain what the issue was, and why it was flagged. 
Explain what you did to refactor or fix your code to address the issue. 
Provide links to the commits where you fixed the issue.

