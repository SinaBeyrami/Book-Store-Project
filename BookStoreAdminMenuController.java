package controller;

import model.BookStore;
import view.enums.messages.BookStoreAdminMenuMessages;

public class BookStoreAdminMenuController {

    public static BookStoreAdminMenuMessages chargeCheck(int amount){
        if (amount <= 0)
            return BookStoreAdminMenuMessages.INVALID_AMOUNT;
        return BookStoreAdminMenuMessages.SUCCESS;
    }

    public static BookStoreAdminMenuMessages addBookCheck(String name , String genre, int price , int cost , BookStore bookStore){
        if (!(genre.matches("^(Story|Science|Magazine)$")))
            return BookStoreAdminMenuMessages.INVALID_GENRE;
        else if (!(name.matches("^[a-z-]+$")))
            return BookStoreAdminMenuMessages.INVALID_BOOK_NAME;
        else if (bookStore.hasBook(name))
            return BookStoreAdminMenuMessages.BOOK_ALREADY_EXISTS;
        else if ((price <= 0) || (cost <= 0))
            return BookStoreAdminMenuMessages.INVALID_AMOUNT;
        return BookStoreAdminMenuMessages.SUCCESS;
    }

    public static BookStoreAdminMenuMessages removeBookCheck(String bookName, BookStore bookStore){
        if (!(bookStore.hasBook(bookName)))
            return BookStoreAdminMenuMessages.BOOK_NOT_FOUND;
        return BookStoreAdminMenuMessages.SUCCESS;
    }

}
