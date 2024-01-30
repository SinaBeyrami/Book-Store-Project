package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {

    SHOW_MENU("^\\s*show\\s+current\\s+menu\\s*$"),
    ENTER_MENU("^\\s*enter\\s+(?<menuName>.+)\\s*$"),
    LOGOUT("^\\s*logout\\s*$");

    public String regex;

    MainMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MainMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }

}
