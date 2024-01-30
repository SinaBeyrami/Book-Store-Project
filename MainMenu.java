package view;

import controller.MainMenuController;
import model.Customer;
import model.BookStoreAdmin;
import model.AppAdmin;
import model.User;
import view.enums.commands.MainMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {

    private User currentUser;

    public MainMenu(User user){
        this.currentUser = user;
    }

    public User getCurrentUser(){
        return this.currentUser;
    }

    public void run (Scanner scanner){
        while(true){
            String command = scanner.nextLine();
            Matcher matcher;
            if (MainMenuCommands.getMatcher(command , MainMenuCommands.SHOW_MENU) != null)
                System.out.println("main menu");
            else if ((matcher = MainMenuCommands.getMatcher(command , MainMenuCommands.ENTER_MENU)) != null){
                switch (MainMenuController.enterMenuChecker(matcher , currentUser)){
                    case INVALID_MENU:
                        System.out.println("enter menu failed: invalid menu name");
                        break;
                    case ACCESS_DENIED:
                        System.out.println("enter menu failed: access denied");
                        break;
                    case SUCCESS_CUSTOMER:
                        System.out.println("enter menu successful: You are in the customer menu!");
                        CustomerMenu customerMenu = new CustomerMenu((Customer) currentUser);
                        customerMenu.run(scanner);
                        break;
                    case SUCCESS_BOOKSTORE_ADMIN:
                        System.out.println("enter menu successful: You are in the book store admin menu!");
                        BookStoreAdminMenu bookStoreAdminMenu = new BookStoreAdminMenu((BookStoreAdmin) currentUser);
                        bookStoreAdminMenu.run(scanner);
                        break;
                    case SUCCESS_APP_ADMIN:
                        System.out.println("enter menu successful: You are in the App admin menu!");
                        AppAdminMenu appAdminMenu = new AppAdminMenu((AppAdmin) currentUser);
                        appAdminMenu.run(scanner);
                        break;
                }
            }
            else if (MainMenuCommands.getMatcher(command , MainMenuCommands.LOGOUT) != null)
                LoginMenu.run(scanner);
            else
                System.out.println("invalid command!");
        }
    }

}
