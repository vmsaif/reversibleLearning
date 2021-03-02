import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// import all the test files
import objects.FlashcardListTest;
import objects.CardSideTest;
import objects.FlashcardTest;
import objects.UserTest;

import data.UserDBTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        // the object classes
        FlashcardListTest.class,
        UserTest.class,
        CardSideTest.class,
        FlashcardTest.class,
        // the data classes
        UserDBTest.class,
})


public class AllTests {
}
