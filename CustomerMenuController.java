package controller;

import model.Customer;
import model.BookStore;
import view.enums.messages.CustomerMenuMessages;

import java.util.regex.Matcher;

public class CustomerMenuController {

    public static CustomerMenuMessages chargeCheck(int amount){
        if (amount <= 0)
            return CustomerMenuMessages.INVALID_AMOUNT;
        return CustomerMenuMessages.SUCCESS;
    }

    public static CustomerMenuMessages showMenuCheck(Matcher matcher){
        String name = matcher.group("bookStoreName");
        if (BookStore.getBookStoreByName(name) == null){
            return CustomerMenuMessages.BOOKSTORE_NOT_FOUND;
        }
        return CustomerMenuMessages.SUCCESS;
    }

    public static CustomerMenuMessages showMenuWithCategoryCheck(Matcher matcher){
        String name = matcher.group("bookStoreName");
        String category = matcher.group("genre");
        if (BookStore.getBookStoreByName(name) == null){
            return CustomerMenuMessages.BOOKSTORE_NOT_FOUND;
        }
        else if (!(category.matches("^(Story|Science|Magazine)$"))){
            return CustomerMenuMessages.INVALID_CATEGORY;
        }
        return CustomerMenuMessages.SUCCESS;
    }

    public static CustomerMenuMessages addToCartCheck(Matcher matcher){
        String bookStoreName = matcher.group("bookStoreName");
        String bookName = matcher.group("bookName");
        if (BookStore.getBookStoreByName(bookStoreName) == null)
            return CustomerMenuMessages.BOOKSTORE_NOT_FOUND;
        else if (BookStore.getBookStoreByName(bookStoreName).getBookByName(bookName) == null)
            return CustomerMenuMessages.BOOK_NOT_FOUND;
        return CustomerMenuMessages.SUCCESS;
    }

    public static CustomerMenuMessages addToCartNCheck(String bookStoreName , String bookName , int number){
        if (BookStore.getBookStoreByName(bookStoreName) == null)
            return CustomerMenuMessages.BOOKSTORE_NOT_FOUND;
        else if (BookStore.getBookStoreByName(bookStoreName).getBookByName(bookName) == null)
            return CustomerMenuMessages.BOOK_NOT_FOUND;
        else if (number <= 0)
            return CustomerMenuMessages.INVALID_NUMBER;
        return CustomerMenuMessages.SUCCESS;
    }

    public static CustomerMenuMessages removeFromCartCheck(Matcher matcher , Customer currentUser){
        String bookStoreName = matcher.group("bookStoreName");
        String bookName = matcher.group("foodName");
        if ((currentUser.getBookByNameFromCart(bookName) == null) || !(currentUser.getBookByNameFromCart(bookName).getBookStore().getName().equals(bookStoreName)))
            return CustomerMenuMessages.NOT_IN_CART;
        return CustomerMenuMessages.SUCCESS;
    }

    public static CustomerMenuMessages removeFromCartNCheck(String bookStoreName , String bookName , int number , Customer currentUser){
        if ((currentUser.getBookByNameFromCart(bookName) == null) || !(currentUser.getBookByNameFromCart(bookName).getBookStore().getName().equals(bookStoreName)))
            return CustomerMenuMessages.NOT_IN_CART;
        else if (number <= 0)
            return CustomerMenuMessages.INVALID_NUMBER;
        if (number > (currentUser.numberOfABookInCart(BookStore.getBookStoreByName(bookStoreName).getBookByName(bookName))))
            return CustomerMenuMessages.NOT_ENOUGH_IN_CART;
        return CustomerMenuMessages.SUCCESS;
    }

    public static CustomerMenuMessages purchaseCartCheck(Customer currentUser){
        if (currentUser.getBalance() < currentUser.totalPrice())
            return CustomerMenuMessages.INADEQUATE_MONEY;
        return CustomerMenuMessages.SUCCESS;
    }

    public static CustomerMenuMessages purchaseCartDCheck(Customer currentUser , String code){
        if (currentUser.getDiscountCodeByCode(code) == null)
            return CustomerMenuMessages.INVALID_DISCOUNT_CODE;
        int discount = currentUser.getDiscountCodeByCode(code).getAmount();
        if (currentUser.getBalance() < (currentUser.totalPrice() - discount))
            return CustomerMenuMessages.INADEQUATE_MONEY;
        return CustomerMenuMessages.SUCCESS;
    }

}
