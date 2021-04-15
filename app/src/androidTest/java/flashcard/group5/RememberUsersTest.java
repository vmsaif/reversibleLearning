package flashcard.group5;


import android.os.SystemClock;

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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RememberUsersTest {
    private final int sleepTime = 500;
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
    private TestUtils testUtils;

    //set up variable
    String user = "user123";
    String password = "pass123";

    @Before
    public void setupTestUtils() {
        testUtils = new TestUtils();
    }


    //test issue #126 - remember users. Check if a registered user was saved and can log in
    @Test
    public void TestLoginNotGuest(){

        // click login
        onView(withId(R.id.button_login)).perform(click());

        // fill the login form
        onView(withId(R.id.username)).perform(typeText(user));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());

        // checked logged in
        onView(withId(R.id.profileActivity)).perform(click());
        onView(allOf(withId(R.id.fullName), withText(user)));
    }

}
