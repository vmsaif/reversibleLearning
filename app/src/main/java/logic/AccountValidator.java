package logic;

public class AccountValidator {

    public static String validateUserName(String userName) {
        String result = null;
        if(userName.contains(" ") || userName.isEmpty())
            result = "Please enter a valid user name";

        else if (userName.length() > 50 || userName.length() < 5)
            result = "Please enter a valid user name in the rage of 5-50 characters";
        return result;
    }

    public static String validatePassword(String password) {
        String result = null;
        if(password.contains(" ") || password.isEmpty())
            result = "Please enter a valid password";

        else if (password.length() > 10 || password.length() < 5)
            result = "Please enter a valid password in the rage of 5-10 characters";
        return result;
    }
}
