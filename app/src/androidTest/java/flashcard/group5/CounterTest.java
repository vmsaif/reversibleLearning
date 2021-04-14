package flashcard.group5;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.orchestrator.instrumentationlistener.OrchestratedInstrumentationListener;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import flashcard.group5.application.MainActivity;
import flashcard.group5.application.R;
import flashcard.group5.utils.TestUtils;
import objects.Flashcard;

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
public class CounterTest {
    private final int sleepTime = 500;
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
    private TestUtils testUtils;

    @Before
    public void setupTestUtils() {
        testUtils = new TestUtils();
    }

    //testing feature #1 - Make a flashcard - put a question and it's answer on it
    @Test
    public void TestCounterFlashCards(){

        String user = "user123";
        String password = "pass123";

        // click login
        onView(withId(R.id.button_login)).perform(click());

        // fill the login form
        onView(withId(R.id.username)).perform(typeText(user));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());


        onView(withId(R.id.makeFlashCard)).perform(click());

        String question = "What group is this?";
        String answer = "Group 5";


        // fill the flashcard
        onView(withId(R.id.editTextTextPersonName2)).perform(typeText(question));
        onView(withId(R.id.editTextTextPersonName3)).perform(typeText(answer));
        closeSoftKeyboard();
        onView(withId(R.id.button_make_flashcard)).perform(click());

        // verify that the flashcard has been created
        onView(withId(R.id.card_front)).check(matches(withText(question)));
        onView(withId(R.id.card_back)).check(matches(withText(answer)));

        onView(withId(R.id.imageView7)).perform(click());
        onView(withId(R.id.profileActivity)).perform(click());

        onView(withId(R.id.cardsCountInt)).check(matches(withText("1")));
        testUtils.deleteFlashcard(new Flashcard(question, answer, user));
    }


}
