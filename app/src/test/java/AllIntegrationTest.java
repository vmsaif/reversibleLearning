import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import Logic.AccountIT;
import Logic.FlashcardLogicIT;

// import all the test files


@RunWith(Suite.class)
@Suite.SuiteClasses({
        // the integration tests
        AccountIT.class,
        FlashcardLogicIT.class,
})


public class AllIntegrationTest {
}
