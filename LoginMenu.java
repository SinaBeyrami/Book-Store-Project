package view;

import controller.LoginMenuController;
import model.Customer;
import model.User;
import view.enums.commands.LoginMenuCommands;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {

    public static void run(Scanner scanner) {
        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;
            if (LoginMenuCommands.getMatcher(command, LoginMenuCommands.SHOW_MENU) != null)
                System.out.println("login menu");
            else if ((matcher = LoginMenuCommands.getMatcher(command , LoginMenuCommands.REGISTER)) != null) {
                String username = matcher.group("username");
                String password = matcher.group("password");
                switch ((LoginMenuController.registerCustomerCheck(username , password))) {
                    case INVALID_USERNAME:
                        System.out.println("register failed: invalid username format");
                        break;
                    case USERNAME_EXISTS:
                        System.out.println("register failed: username already exists");
                        break;
                    case INVALID_PASSWORD:
                        System.out.println("register failed: invalid password format");
                        break;
                    case WEAK_PASSWORD:
                        System.out.println("register failed: weak password");
                        break;
                    case SUCCESS:
                        Customer newCustomer = new Customer(username, password);
                        System.out.println("register successful");
                        break;
                }
            }
            else if ((matcher = LoginMenuCommands.getMatcher(command , LoginMenuCommands.LOGIN)) != null){
                switch (LoginMenuController.loginCheck(matcher)){
                    case USERNAME_NOT_EXISTS:
                        System.out.println("login failed: username not found");
                        break;
                    case INCORRECT_PASSWORD:
                        System.out.println("login failed: incorrect password");
                        break;
                    case SUCCESS:
                        System.out.println("login successful");
                        MainMenu mainMenu = new MainMenu(User.getUserByUsername(matcher.group("username")));
                        mainMenu.run(scanner);
                        break;
                }
            }
            else if ((matcher = LoginMenuCommands.getMatcher(command , LoginMenuCommands.CHANGE_PASSWORD)) != null){
                String username = matcher.group("username");
                String newPassword = matcher.group("newPassword");
                switch (LoginMenuController.changeChecker(matcher)){
                    case USERNAME_NOT_EXISTS:
                        System.out.println("password change failed: username not found");
                        break;
                    case INCORRECT_PASSWORD:
                        System.out.println("password change failed: incorrect password");
                        break;
                    case INVALID_PASSWORD:
                        System.out.println("password change failed: invalid new password");
                        break;
                    case WEAK_PASSWORD:
                        System.out.println("password change failed: weak new password");
                        break;
                    case SUCCESS:
                        User.getUserByUsername(username).changePassword(newPassword);
                        System.out.println("password change successful");
                        break;
                }
            }
            else if ((matcher = LoginMenuCommands.getMatcher(command , LoginMenuCommands.REMOVE_ACCOUNT)) != null){
                switch (LoginMenuController.deleteChecker(matcher)){
                    case USERNAME_NOT_EXISTS:
                        System.out.println("remove account failed: username not found");
                        break;
                    case INCORRECT_PASSWORD:
                        System.out.println("remove account failed: incorrect password");
                        break;
                    case SUCCESS:
                        User.deleteUser(User.getUserByUsername(matcher.group("username")));
                        System.out.println("remove account successful");
                        break;
                }
            }
            else if (LoginMenuCommands.getMatcher(command , LoginMenuCommands.EXIT) != null)
                System.exit(0);
            else
                System.out.println("invalid command!");
        }
    }
}
