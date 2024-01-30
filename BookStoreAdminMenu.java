package view;

import controller.BookStoreAdminMenuController;
import model.Book;
import model.BookStore;
import model.BookStoreAdmin;
import view.enums.commands.BookStoreAdminMenuCommands;
import java.util.Scanner;
import java.util.regex.Matcher;

public class BookStoreAdminMenu {

    private BookStoreAdmin currentUser;

    public BookStoreAdminMenu(BookStoreAdmin currentUser){
        this.currentUser = currentUser;
    }

    public void run(Scanner scanner){
        while(true){
            String command = scanner.nextLine();
            Matcher matcher;
            if ((matcher = BookStoreAdminMenuCommands.getMatcher(command , BookStoreAdminMenuCommands.CHARGE_BOOKSTORE)) != null){
                int amount = Integer.parseInt(matcher.group("amount"));
                switch (BookStoreAdminMenuController.chargeCheck(amount)){
                    case INVALID_AMOUNT:
                        System.out.println("charge account failed: invalid cost or price");
                        break;
                    case SUCCESS:
                        BookStore.getBookStoreByName(currentUser.getUsername()).increaseBalance(amount);
                        System.out.println("charge account successful");
                        break;
                }
            }
            else if (BookStoreAdminMenuCommands.getMatcher(command , BookStoreAdminMenuCommands.SHOW_BALANCE) != null)
                System.out.println(BookStore.getBookStoreByName(currentUser.getUsername()).getBalance());
            else if ((matcher = BookStoreAdminMenuCommands.getMatcher(command , BookStoreAdminMenuCommands.ADD_BOOK)) != null){
                String name = matcher.group("name");
                String genre = matcher.group("genre");
                int price = Integer.parseInt(matcher.group("price"));
                int cost = Integer.parseInt((matcher.group("cost")));
                switch(BookStoreAdminMenuController.addBookCheck(name , genre, price , cost , BookStore.getBookStoreByName(currentUser.getUsername()))){
                    case INVALID_GENRE:
                        System.out.println("add book failed: invalid genre");
                        break;
                    case INVALID_BOOK_NAME:
                        System.out.println("add book failed: invalid book name");
                        break;
                    case BOOK_ALREADY_EXISTS:
                        System.out.println("add book failed: book already exists");
                        break;
                    case INVALID_AMOUNT:
                        System.out.println("add book failed: invalid cost or price");
                        break;
                    case SUCCESS:
                        Book newBook = new Book(name , genre, price , cost , BookStore.getBookStoreByName(currentUser.getUsername()));
                        BookStore.getBookStoreByName(currentUser.getUsername()).addBook(newBook);
                        System.out.println("add book successful");
                        break;
                }
            }
            else if ((matcher = BookStoreAdminMenuCommands.getMatcher(command , BookStoreAdminMenuCommands.REMOVE_BOOK)) != null){
                String bookName = matcher.group("name");
                switch(BookStoreAdminMenuController.removeBookCheck(bookName, BookStore.getBookStoreByName(currentUser.getUsername()))) {
                    case BOOK_NOT_FOUND:
                        System.out.println("remove book failed: book not found");
                        break;
                    case SUCCESS:
                        BookStore.getBookStoreByName(currentUser.getUsername()).removeBookByName(bookName);

                        break;
                }
            }
            else if (BookStoreAdminMenuCommands.getMatcher(command , BookStoreAdminMenuCommands.SHOW_MENU) != null)
                System.out.println("book store admin menu");
            else if (BookStoreAdminMenuCommands.getMatcher(command , BookStoreAdminMenuCommands.LOGOUT) != null)
                LoginMenu.run(scanner);
            else
                System.out.println("invalid command!");
        }
    }

}
