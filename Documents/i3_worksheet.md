Iteration 3 Worksheet
=====================

What technical debt has been cleaned up
-----------------

* https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/merge_requests/62/diffs#8c55c3ccc257e5907959013f99656e4c8ec3903e_16_16

<p> Throughout this commit, you can find instances where we recapitalize 'card' and rename folders to cardshelf. This was because we were running into problems with the new methods we were creating, 
and our confusing terminology in the past required us to create better names for our code. This was unintended reckless debt.

What technical debt did you leave?
-----------------

We would like to include a counter for the folders a user has created, but we can't. This is inadvertent, prudent debt. The way we set up 
folders to work in the database does not lend well to counting, and as a result of this we would need to refactor large portions of the folder 
implementation (and spend a fair amount of development time) and we don't feel it's an important enough issue to hamper our other features going forward.
In the end, while it would be nice to see a count of all the topics you've ever needed a folder for, it seemed less important than counting your flashcards.

Discuss a Feature or User Story that was cut/re-prioritized
-----------------

* https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/issues/4

This feature was going to allow us to create 'tests' like you would take in class. You would choose from your flashcards and it would present you 
a page of flashcard questions, and you would need to write out the answers. This was re-prioritized because we realized creating an enjoyable 
and comfortable flashcard storing and grouping experience is the primary goal, and spending time trying to implement this feature that is so 
unlike any of our others would have damaged the quality of the other features.

Acceptance test/end-to-end
-----------------

[UpdateProfileTest](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/commit/bc2330eae644afd687dce7cc8c4fa4f23a7cd4bf#2a6ede2cd6e5b02f0509e3bdd18ead16061d95f0_0_42)

In the UpdateProfileTest, we test the entire process of creating a user profile, and the process of changing 
a user's profile information. This tests our edit profile feature, while also testing our create profile feature. 
Because of the way we set up our variables, we can be sure that there is no other sources of error in the test, 
because the second username is a constant variable, the only way the test can fail is if the changed password 
is incorrectly updated, creating a solid and not flaky test.

Acceptance test, untestable
-----------------

Because of the way espresso checks for text validation, and because our flashcards are not shown in a specific order, we cannot be sure when exactly we will see the flashcard 
we are trying to verify for. This creates a problem, because we cannot create an acceptance test for it without fully emptying the database first. 
Therefore, without proper and perfect instructions, any acceptance test will fail unless we wipe the database or instruct the marker to do so. As a result of this, 
due to the way espresso checks for test validation and because of our database, creating a flashcard is untestable.

Velocity/teamwork
-----------------
* https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/milestones/2
* https://code.cs.umanitoba.ca/3350-winter-2021-a02/group5/-/milestones/3

Just by looking at our time estimates from iteration 2, you can see that we decided to commit to doing two and a half months of work throughout the iteration. 
This was absurd, and while our estimates were obviously generous, in iteration 3 we committed to 1 and a half months of work, which is much more reasonable. 
Obviously this is still a fair about for students in multiple courses, but just those numbers alone show that we are managing our time estimates more realistically
