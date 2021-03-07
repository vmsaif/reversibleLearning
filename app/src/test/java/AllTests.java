import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// import all the test files
import Logic.LoginTest;
import objects.FlashcardListTest;
import objects.FlashcardTest;
import objects.UserTest;

import data.UserDBTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        // the object classes
        FlashcardListTest.class,
        UserTest.class,
        FlashcardTest.class,
        // the data classes
        UserDBTest.class,
        LoginTest.class
})


public class AllTests {
}
