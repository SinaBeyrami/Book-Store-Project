package controller;

import model.BookStore;
import model.User;
import view.enums.messages.AppAdminMenuMessages;

import java.util.regex.Matcher;

public class AppAdminMenuController {

    public static AppAdminMenuMessages registerCustomerCheck(String username , String password , String type){
        if (!(username.matches("^(?=.*[a-zA-Z])[a-zA-Z0-9_]+$")))
            return AppAdminMenuMessages.INVALID_USERNAME;
        else if (User.getUserByUsername(username) != null)
            return AppAdminMenuMessages.USERNAME_EXISTS;
        else if(!(password.matches("^[a-zA-Z0-9_]+$")))
            return AppAdminMenuMessages.INVALID_PASSWORD;
        else if (!(password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9_]{5,}$")))
            return AppAdminMenuMessages.WEAK_PASSWORD;
        else if (!(type.matches("^[a-z-]+$")))
            return AppAdminMenuMessages.INVALID_TYPE;
        else
            return AppAdminMenuMessages.SUCCESS;
    }

    public static AppAdminMenuMessages removeBookStoreCheck(Matcher matcher){
        String name = matcher.group("name");
        if (BookStore.getBookStoreByName(name) == null)
            return AppAdminMenuMessages.NAME_NOT_FOUND;
        return AppAdminMenuMessages.SUCCESS;
    }

    public static AppAdminMenuMessages setDiscountCheck(Matcher matcher){
        String username = matcher.group("username");
        int amount = Integer.parseInt(matcher.group("amount"));
        String code = matcher.group("code");
        if (User.getUserByUsername(username) == null)
            return AppAdminMenuMessages.USERNAME_NOT_EXISTS;
        else if (amount <= 0)
            return AppAdminMenuMessages.INVALID_AMOUNT;
        if (!(code.matches("^[a-zA-Z0-9]+$")))
            return AppAdminMenuMessages.INVALID_CODE;
        return AppAdminMenuMessages.SUCCESS;
    }

}
