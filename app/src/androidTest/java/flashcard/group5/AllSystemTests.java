package flashcard.group5;

import androidx.test.filters.LargeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@LargeTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegisterTest.class,
        RememberUsersTest.class,
        UpdateProfileTest.class,
        MakeFlashCardTest.class,
        DeleteFlashCardTest.class,
        CounterTest.class,
        FlashCardFoldersTest.class,
        HideAnswersTest.class,
        ViewFlashCardsTest.class,
})


public class AllSystemTests {
}
