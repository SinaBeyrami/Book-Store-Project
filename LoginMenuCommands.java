package view.enums.commands;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {

    EXIT("^\\s*exit\\s*$"),
    SHOW_MENU("^\\s*show\\s+current\\s+menu\\s*"),
    REGISTER("^\\s*register\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*$"),
    LOGIN("^\\s*login\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*$"),
    CHANGE_PASSWORD("^\\s*change\\s+password\\s+(?<username>\\S+)\\s+(?<oldPassword>\\S+)\\s+(?<newPassword>\\S+)\\s*$"),
    REMOVE_ACCOUNT("^\\s*remove\\s+account\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*$");

    public String regex;

    LoginMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, LoginMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }

}