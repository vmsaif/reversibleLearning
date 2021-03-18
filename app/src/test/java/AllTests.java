import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import Logic.AccountTest;
import Logic.FlashcardLogicTest;
import data.FlashcardPersistenceTest;
import data.UserDBTest;
import data.UserPersistenceTest;
import objects.FlashcardListTest;
import objects.FlashcardTest;
import objects.UserTest;

// import all the test files


@RunWith(Suite.class)
@Suite.SuiteClasses({
        // the object classes
        FlashcardListTest.class,
        UserTest.class,
        FlashcardTest.class,
        // the data classes
        UserPersistenceTest.class,
        FlashcardPersistenceTest.class,
        UserDBTest.class,
        // the logic classes
        AccountTest.class,
        FlashcardLogicTest.class,
})


public class AllTests {
}
