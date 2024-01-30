package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum BookStoreAdminMenuCommands {
    CHARGE_BOOKSTORE("^\\s*charge\\s+account\\s+(?<amount>\\S+)\\s*$"),
    SHOW_BALANCE("^\\s*show\\s+balance\\s*"),
    ADD_BOOK("^\\s*add\\s+book\\s+(?<name>\\S+)\\s+(?<genre>\\S+)\\s+(?<price>\\S+)\\s+(?<cost>\\S+)\\s*$"),
    REMOVE_BOOK("^\\s*remove\\s+book\\s+(?<name>\\S+)\\s*$"),
    SHOW_MENU("^\\s*show\\s+current\\s+menu\\s*"),
    LOGOUT("^\\s*logout\\s*$");

    public String regex;

    BookStoreAdminMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, BookStoreAdminMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }

}
