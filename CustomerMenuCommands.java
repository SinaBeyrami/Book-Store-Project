package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CustomerMenuCommands {
    CHARGE_ACCOUNT("^\\s*charge\\s+account\\s+(?<amount>\\S+)\\s*$"),
    SHOW_BALANCE("^\\s*show\\s+balance\\s*"),
    SHOW_BOOKSTORES("^\\s*show\\s+book store\\s*$"),
    SHOW_BOOKSTORES_WITH_TYPE("^\\s*show\\s+book store\\s+-t\\s+(?<type>\\S+)\\s*$"),
    SHOW_MENU("^\\s*show\\s+menu\\s+(?<bookStoreName>\\S+)\\s*$"),
    SHOW_MENU_WITH_GENRE("^\\s*show\\s+menu\\s+(?<bookStoreName>\\S+)\\s+-g\\s+(?<genre>\\S+)\\s*$"),
    ADD_TO_CART("^\\s*add\\s+to\\s+cart\\s+(?<bookStoreName>\\S+)\\s+(?<bookName>\\S+)\\s*$"),
    ADD_TO_CART_N("^\\s*add\\s+to\\s+cart\\s+(?<bookStoreName>\\S+)\\s+(?<bookName>\\S+)\\s+-n\\s+(?<number>\\S+)\\s*$"),
    REMOVE_FROM_CART("^\\s*remove\\s+from\\s+cart\\s+(?<bookStoreName>\\S+)\\s+(?<bookName>\\S+)\\s*$"),
    REMOVE_FROM_CART_N("^\\s*remove\\s+from\\s+cart\\s+(?<bookStoreName>\\S+)\\s+(?<bookName>\\S+)\\s+-n\\s+(?<number>\\S+)\\s*$"),
    SHOW_CART("^\\s*show\\s+cart\\s*$"),
    SHOW_DISCOUNTS("^\\s*show\\s+discounts\\s*$"),
    PURCHASE_CART("^\\s*purchase\\s+cart\\s*$"),
    PURCHASE_CART_D("^\\s*purchase\\s+cart\\s+-d\\s+(?<discountCode>\\S+)\\s*$"),
    SHOW_CURRENT_MENU("^\\s*show\\s+current\\s+menu\\s*"),
    LOGOUT("^\\s*logout\\s*$");


    public String regex;

    CustomerMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, CustomerMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }

}
