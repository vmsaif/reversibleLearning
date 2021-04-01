package flashcard.group5;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import flashcard.group5.application.MainActivity;
import flashcard.group5.application.R;
import flashcard.group5.utils.TestUtils;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FlashcardTest {
    private final int sleepTime = 500;
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
    private TestUtils testUtils;

    @Before
    public void setupTestUtils() {
        testUtils = new TestUtils();
    }

    @Test
    public void TestCreateFlashcard(){
        String question = "What group is this?";
        String answer = "Group 5";

        // click guest
        onView(withId(R.id.button_guestLogin)).perform(click());
        onView(withId(R.id.makeFlashCard)).perform(click());

        // fill the flashcard
        onView(withId(R.id.editTextTextPersonName2)).perform(typeText(question));
        onView(withId(R.id.editTextTextPersonName3)).perform(typeText(answer));
        closeSoftKeyboard();
        onView(withId(R.id.button_make_flashcard)).perform(click());

        // verify that the flashcard has been created
        onView(withId(R.id.card_front)).check(matches(withText(question)));
        onView(withId(R.id.card_back)).check(matches(withText(answer)));

        // check if it is added to the shelf
        onView(withId(R.id.imageView7)).perform(click());
        onView(withId(R.id.Folders)).perform(click());
        onView(allOf(withId(R.id.shelfCard), withText(question)));
        onView(allOf(withId(R.id.shelfCard), withText(answer)));
    }

    @Test
    public void TestDeleteFlashcardAfterCreation(){
        String question = "This question will be deleted right away";
        String answer = "no answer in this textBox";

        // click guest
        onView(withId(R.id.button_guestLogin)).perform(click());
        onView(withId(R.id.makeFlashCard)).perform(click());

        // fill the flashcard
        onView(withId(R.id.editTextTextPersonName2)).perform(typeText(question));
        onView(withId(R.id.editTextTextPersonName3)).perform(typeText(answer));
        closeSoftKeyboard();
        onView(withId(R.id.button_make_flashcard)).perform(click());

        // verify that the flashcard has been created
        onView(withId(R.id.card_front)).check(matches(withText(question)));
        onView(withId(R.id.card_back)).check(matches(withText(answer)));

        // delete the card
        onView(withId(R.id.delete_button)).perform(click());

        // check if it is not added to the shelf
        onView(withId(R.id.Folders)).perform(click());
        onView(allOf(withId(R.id.shelfCard), not(withText(question))));
        onView(allOf(withId(R.id.shelfCard), not(withText(answer))));
    }

    @Test
    public void TestDeleteFlashcardFromShelf(){
        String question = "A new question that will be deleted on the shelf";
        String answer = "A new answer";

        // click guest
        onView(withId(R.id.button_guestLogin)).perform(click());
        onView(withId(R.id.makeFlashCard)).perform(click());

        // fill the flashcard
        onView(withId(R.id.editTextTextPersonName2)).perform(typeText(question));
        onView(withId(R.id.editTextTextPersonName3)).perform(typeText(answer));
        closeSoftKeyboard();
        onView(withId(R.id.button_make_flashcard)).perform(click());

        // verify that the flashcard has been created
        onView(withId(R.id.card_front)).check(matches(withText(question)));
        onView(withId(R.id.card_back)).check(matches(withText(answer)));

        // check if it is added to the shelf
        onView(withId(R.id.imageView7)).perform(click());
        onView(withId(R.id.Folders)).perform(click());
        onView(allOf(withId(R.id.shelfCard), withText(question)));
        onView(allOf(withId(R.id.shelfCard), withText(answer)));

        // delete the card
        onView(withId(R.id.delete_button2)).perform(click());

        // check if it doesn't exists
        onView(allOf(withId(R.id.shelfCard), not(withText(question))));
        onView(allOf(withId(R.id.shelfCard), not(withText(answer))));

    }

}
