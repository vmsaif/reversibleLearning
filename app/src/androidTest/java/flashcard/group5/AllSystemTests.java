package flashcard.group5;

import androidx.test.filters.LargeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@LargeTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountTest.class,
        FlashcardTest.class,
})


public class AllSystemTests {
}
