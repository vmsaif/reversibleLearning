package logic;

import objects.User;

public class LoggedUser {
    public static User loggedUser = null;

    public static void setLoggedUser(User user) {loggedUser = user;}

    public static User getLoggedUser(){return loggedUser;}
}
