package controller;

import model.Application;
import model.User;
import view.enums.messages.LoginMenuMessages;

import java.util.regex.Matcher;

public class LoginMenuController {

    public static LoginMenuMessages registerCustomerCheck(String username , String password){
        if (!(username.matches("^(?=.*[a-zA-Z])[a-zA-Z0-9_]+$")))
            return LoginMenuMessages.INVALID_USERNAME;
        else if (User.getUserByUsername(username) != null)
            return LoginMenuMessages.USERNAME_EXISTS;
        else if(!(password.matches("^[a-zA-Z0-9_]+$")))
            return LoginMenuMessages.INVALID_PASSWORD;
        else if (!(password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9_]{5,}$")))
            return LoginMenuMessages.WEAK_PASSWORD;
        else
            return LoginMenuMessages.SUCCESS;
    }

    public static LoginMenuMessages loginCheck(Matcher matcher){
        String username = matcher.group("username");
        String password = matcher.group("password");
        if (User.getUserByUsername(username) == null)
            return LoginMenuMessages.USERNAME_NOT_EXISTS;
        else if (!(User.getUserByUsername(username).getPassword().equals(password)))
            return LoginMenuMessages.INCORRECT_PASSWORD;
        return LoginMenuMessages.SUCCESS;
    }

    public static LoginMenuMessages changeChecker(Matcher matcher){
        String username = matcher.group("username");
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");
        if (User.getUserByUsername(username) == null)
            return LoginMenuMessages.USERNAME_NOT_EXISTS;
        else if (!(User.getUserByUsername(username).getPassword().equals(oldPassword)))
            return LoginMenuMessages.INCORRECT_PASSWORD;
        else if (!(newPassword.matches("^[a-zA-Z0-9_]+$")))
            return LoginMenuMessages.INVALID_PASSWORD;
        else if (!(newPassword.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9_]{5,}$")))
            return LoginMenuMessages.WEAK_PASSWORD;
        return LoginMenuMessages.SUCCESS;
    }

    public static LoginMenuMessages deleteChecker(Matcher matcher){
        String username = matcher.group("username");
        String password = matcher.group("password");
        if (User.getUserByUsername(username) == null)
            return LoginMenuMessages.USERNAME_NOT_EXISTS;
        else if (!(User.getUserByUsername(username).getPassword().equals(password)))
            return LoginMenuMessages.INCORRECT_PASSWORD;
        return LoginMenuMessages.SUCCESS;
    }
}
