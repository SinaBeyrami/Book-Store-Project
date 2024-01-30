package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum AppAdminMenuCommands {

    ADD_BOOKSTORE("^\\s*add\\s+book store\\s+(?<name>\\S+)\\s+(?<password>\\S+)\\s+(?<type>(\\S+))\\s*$"),
    SHOW_BOOKSTORE("^\\s*show\\s+book store\\s*$"),
    SHOW_BOOKSTORE_WITH_TYPE("^\\s*show\\s+book store\\s+-t\\s+(?<type>\\S+)\\s*$"),
    REMOVE_BOOKSTORE("^\\s*remove\\s+book store\\s+(?<name>\\S+)\\s*$"),
    SET_DISCOUNT("^\\s*set\\s+discount\\s+(?<username>\\S+)\\s+(?<amount>\\S+)\\s+(?<code>(\\S+))\\s*$"),
    SHOW_DISCOUNTS("^\\s*show\\s+discounts\\s*"),
    SHOW_MENU("^\\s*show\\s+current\\s+menu\\s*"),
    LOGOUT("^\\s*logout\\s*$");


    public String regex;

    AppAdminMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, AppAdminMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }

}
