package flashcard.group5;


import android.content.Intent;
import android.os.SystemClock;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.google.android.material.textfield.TextInputLayout;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import flashcard.group5.application.MainActivity;
import flashcard.group5.application.R;
import flashcard.group5.utils.TestUtils;
import presentation.LoginActivity;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UpdateProfileTest {
    private final int sleepTime = 500;
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
    private TestUtils testUtils;

    //set up variables
    String user = "test1@test.com";
    String password = "usertest";
    String newUser = "test2@test.com";

    @Before
    public void setupTestUtils() { testUtils = new TestUtils(); }


    //test issue #125 - update user's information. Register an account -> login -> change username -> login again wit
    //the new user name
    @Test
    public void updateUserName() {
        // click register
        onView(withId(R.id.button_login)).perform(click());
        onView(withId(R.id.register)).perform(click());

        // fill the registration form
        onView(withId(R.id.username)).perform(typeText(user));
        onView(withId(R.id.password)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.button_register)).perform(click());

        // wait to load
        SystemClock.sleep(sleepTime);

        // fill the login form
        onView(withId(R.id.username)).perform(typeText(user));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());

        //change the user name to a new one and update profile
        onView(withId(R.id.profileActivity)).perform(click());
        onView(withId(R.id.profileUserNameField)).perform(clearText());
        onView(withId(R.id.profileUserNameField)).perform(typeText(newUser));
        onView(withId(R.id.profileUpdateButton)).perform(click());

    }

    //test if the update worked and we can login with the new user name
    @Test
    public void newlogin() {

        // click login
        onView(withId(R.id.button_login)).perform(click());

        // wait to load
        SystemClock.sleep(sleepTime);

        // fill the login form
        onView(withId(R.id.username)).perform(typeText(newUser));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());

        // checked logged in
        onView(withId(R.id.profileActivity)).perform(click());
        onView(allOf(withId(R.id.fullName), withText(user)));
        testUtils.deleteUser();
    }
}
