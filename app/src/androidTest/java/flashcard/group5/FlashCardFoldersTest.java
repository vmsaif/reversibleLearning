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

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FlashCardFoldersTest {
    private final int sleepTime = 500;
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
    private TestUtils testUtils;

    @Before
    public void setupTestUtils() {
        testUtils = new TestUtils();
    }

    //testing feature #9 - organizing flashcards in folders by labels
    @Test
    public void testAddFolder(){
        // click guest
        onView(withId(R.id.button_guestLogin)).perform(click());

        //create a folder
        onView(withId(R.id.AllFolders)).perform(click());
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.folder_name)).perform(typeText("Math"));
        closeSoftKeyboard();
        onView(withId(R.id.button4)).perform(click());
        onView(withId(R.id.AllFolders)).perform(click());

        //delete the folder so we can re-run the test as folder name is primary key
        onView(allOf(withText("Math"))).perform(click());
        onView(withId(R.id.deleteFolder)).perform(click());

        testUtils.deleteFolder("Math");
    }

}
