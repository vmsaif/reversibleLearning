package Logic;

import org.junit.Test;

import logic.AccountValidator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AccountValidatorTest {


    @Test
    public void TestValidateUsername(){
        // test common username
        assertNull(AccountValidator.validateUserName("username"));
        assertNull(AccountValidator.validateUserName("Username"));
        assertNull(AccountValidator.validateUserName("Us3rn4me"));
        assertNull(AccountValidator.validateUserName("100Username2020$22"));
        // test invalid username
        assertNotNull(AccountValidator.validateUserName(""));
        assertNotNull(AccountValidator.validateUserName("     "));
        assertNotNull(AccountValidator.validateUserName(" s s s "));
        assertNotNull(AccountValidator.validateUserName(" . "));
        assertNotNull(AccountValidator.validateUserName("a"));
        assertNotNull(AccountValidator.validateUserName("1"));
        assertNotNull(AccountValidator.validateUserName("adfsadsfadsfafasdfahlfasdfljasdlkfjaskldfjqierqhoiwehdjagnljsdghaljksdhgkasngdlja,gnmadsgnakjhgdaksghakjldshkljfahljkahdsfk"));
    }


    @Test
    public void TestValidatePassword(){
        // test valid password
        assertNull(AccountValidator.validatePassword("password"));
        assertNull(AccountValidator.validatePassword("pass123"));
        assertNull(AccountValidator.validatePassword("2.word"));
        assertNull(AccountValidator.validatePassword("PASSWORD"));
        assertNull(AccountValidator.validatePassword("1PASSWORD#"));
        // test invalid password
        assertNotNull(AccountValidator.validatePassword(""));
        assertNotNull(AccountValidator.validatePassword("    "));
        assertNotNull(AccountValidator.validatePassword(" . . ."));
        assertNotNull(AccountValidator.validatePassword("a"));
        assertNotNull(AccountValidator.validatePassword("ast"));
        assertNotNull(AccountValidator.validatePassword("1234"));
        assertNotNull(AccountValidator.validatePassword("averylongpassword"));
        assertNotNull(AccountValidator.validatePassword("124143512351612690126039169120369016"));
    }


}
