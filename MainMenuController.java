package controller;

import model.Customer;
import model.BookStoreAdmin;
import model.AppAdmin;
import model.User;
import view.enums.messages.MainMenuMessages;

import java.util.regex.Matcher;

public class MainMenuController {

    public static MainMenuMessages enterMenuChecker(Matcher matcher , User currentUser){
        if (!(matcher.group("menuName")).trim().matches("^(customer\\s+menu|book store\\s+admin\\s+menu|App\\s+admin\\s+menu)"))
            return MainMenuMessages.INVALID_MENU;
        else if ((matcher.group("menuName").matches("^customer\\s+menu\\s*$")) && (currentUser instanceof Customer))
            return MainMenuMessages.SUCCESS_CUSTOMER;
        else if ((matcher.group("menuName").matches("^book store\\s+admin\\s+menu\\s*$")) && (currentUser instanceof BookStoreAdmin))
            return MainMenuMessages.SUCCESS_BOOKSTORE_ADMIN;
        else if ((matcher.group("menuName").matches("^App\\s+admin\\s+menu\\s*$")) && (currentUser instanceof AppAdmin))
            return MainMenuMessages.SUCCESS_APP_ADMIN;
        return MainMenuMessages.ACCESS_DENIED;
    }

}
