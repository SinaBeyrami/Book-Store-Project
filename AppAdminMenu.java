package view;

import controller.AppAdminMenuController;
import model.*;
import view.enums.commands.AppAdminMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class AppAdminMenu {

    private AppAdmin currentUser;

    public AppAdminMenu(AppAdmin currentUser){
        this.currentUser = currentUser;
//        System.out.println("#log: an object from AppAdminMenu class created.");
    }

    public void run(Scanner scanner){
//        System.out.println("#log: AppAdminMenu is running ...");
        while(true){
            String command = scanner.nextLine();
            Matcher matcher;
            if ((matcher = AppAdminMenuCommands.getMatcher(command , AppAdminMenuCommands.ADD_BOOKSTORE)) != null){
                String username = matcher.group("name");
                String password = matcher.group("password");
                String type = matcher.group("type");
                switch (AppAdminMenuController.registerCustomerCheck(username , password , type)){
                    case INVALID_USERNAME:
                        System.out.println("add book store failed: invalid username format");
                        break;
                    case USERNAME_EXISTS:
                        System.out.println("add book store failed: username already exists");
                        break;
                    case INVALID_PASSWORD:
                        System.out.println("add book store failed: invalid password format");
                        break;
                    case WEAK_PASSWORD:
                        System.out.println("add book store failed: weak password");
                        break;
                    case INVALID_TYPE:
                        System.out.println("add book store failed: invalid type format");
                        break;
                    case SUCCESS:
                        BookStore newBookStore = new BookStore(username , password , type);
                        BookStoreAdmin newRestaurantAdmin = new BookStoreAdmin(username , password);
                        System.out.println("add book store successful");
                        break;
                }
            }
            else if ((matcher = AppAdminMenuCommands.getMatcher(command , AppAdminMenuCommands.SHOW_BOOKSTORE)) != null){
                if (BookStore.isEmpty())
                    BookStore.nothing();
                else
                    System.out.println(BookStore.showBookStores());
            }
            else if ((matcher = AppAdminMenuCommands.getMatcher(command , AppAdminMenuCommands.SHOW_BOOKSTORE_WITH_TYPE)) != null) {
                if (BookStore.isEmptyWithType(matcher.group("type")))
                    BookStore.nothing();
                else
                    System.out.println(BookStore.showBookStoresWithType(matcher.group("type")));
            }
            else if ((matcher = AppAdminMenuCommands.getMatcher(command , AppAdminMenuCommands.REMOVE_BOOKSTORE)) != null){
                switch (AppAdminMenuController.removeBookStoreCheck(matcher)){
                    case NAME_NOT_FOUND:
                        System.out.println("remove book store failed: book store not found");
                        break;
                    case SUCCESS:
                        User.deleteUser(User.getUserByUsername(matcher.group("name")));
                        BookStore.removeBookStore(BookStore.getBookStoreByName(matcher.group("name")));
                        break;
                }
            }
            else if ((matcher = AppAdminMenuCommands.getMatcher(command , AppAdminMenuCommands.SET_DISCOUNT)) != null){
                switch (AppAdminMenuController.setDiscountCheck(matcher)){
                    case USERNAME_NOT_EXISTS:
                        System.out.println("set discount failed: username not found");
                        break;
                    case INVALID_AMOUNT:
                        System.out.println("set discount failed: invalid amount");
                        break;
                    case INVALID_CODE:
                        System.out.println("set discount failed: invalid code format");
                        break;
                    case SUCCESS:
                        DiscountCode discountCode = new DiscountCode(Integer.parseInt(matcher.group("amount")) , matcher.group("code"));
                        ((Customer)Customer.getUserByUsername(matcher.group("username"))).addDiscountCode(discountCode);
                        System.out.println("set discount successful");
                }
            }
            else if (AppAdminMenuCommands.getMatcher(command , AppAdminMenuCommands.SHOW_DISCOUNTS) != null) {
                if (DiscountCode.isEmpty())
                    DiscountCode.nothing();
                else
                    System.out.println(DiscountCode.showAllCodes());
            }
            else if (AppAdminMenuCommands.getMatcher(command , AppAdminMenuCommands.SHOW_MENU) != null)
                System.out.println("App admin menu");
            else if (AppAdminMenuCommands.getMatcher(command , AppAdminMenuCommands.LOGOUT) != null)
                LoginMenu.run(scanner);
            else
                System.out.println("invalid command!");
        }
    }


}
