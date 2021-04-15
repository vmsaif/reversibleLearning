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
public class ViewFlashCardsTest {
    private final int sleepTime = 500;
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
    private TestUtils testUtils;

    //set up variables
    String question1 = "question 1?";
    String answer1 = "answer 1";

    String question2 = "question 2?";
    String answer2 = "answer2";

    String question3 = "question 3?";
    String answer3 = "answer3";

    String user = "guest";

    @Before
    public void setupTestUtils() {
        testUtils = new TestUtils();
    }

    //testing feature #1 - Make a flashcard - put a question and it's answer on it
    @Test
    public void TestCreateFlashcard(){

        // click guest
        onView(withId(R.id.button_guestLogin)).perform(click());

        onView(withId(R.id.makeFlashCard)).perform(click());
        // fill the flashcard 1
        onView(withId(R.id.editTextTextPersonName2)).perform(typeText(question1));
        onView(withId(R.id.editTextTextPersonName3)).perform(typeText(answer1));
        closeSoftKeyboard();
        onView(withId(R.id.button_make_flashcard)).perform(click());
        onView(withId(R.id.imageView7)).perform(click());


        onView(withId(R.id.makeFlashCard)).perform(click());
        // fill the flashcard 2
        onView(withId(R.id.editTextTextPersonName2)).perform(typeText(question2));
        onView(withId(R.id.editTextTextPersonName3)).perform(typeText(answer2));
        closeSoftKeyboard();
        onView(withId(R.id.button_make_flashcard)).perform(click());
        onView(withId(R.id.imageView7)).perform(click());

        onView(withId(R.id.makeFlashCard)).perform(click());
        // fill the flashcard 3
        onView(withId(R.id.editTextTextPersonName2)).perform(typeText(question3));
        onView(withId(R.id.editTextTextPersonName3)).perform(typeText(answer3));
        closeSoftKeyboard();
        onView(withId(R.id.button_make_flashcard)).perform(click());
        onView(withId(R.id.imageView7)).perform(click());

        onView(withId(R.id.cardShelf)).perform(click());

        //view the flashcards
        onView(withId(R.id.next_button)).perform(click());
        onView(withId(R.id.next_button)).perform(click());
        onView(withId(R.id.next_button)).perform(click());
        onView(withId(R.id.next_button)).perform(click());
        onView(withId(R.id.next_button)).perform(click());
        onView(withId(R.id.next_button)).perform(click());
        onView(withId(R.id.next_button)).perform(click());
        testUtils.deleteFlashcard(new Flashcard(question1,answer1,user));
        testUtils.deleteFlashcard(new Flashcard(question2,answer2,user));
        testUtils.deleteFlashcard(new Flashcard(question3,answer3,user));
    }
}
