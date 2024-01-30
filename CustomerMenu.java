package view;

import controller.CustomerMenuController;
import model.Customer;
import model.BookStore;
import view.enums.commands.CustomerMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class CustomerMenu {

    private Customer currentUser;

    public CustomerMenu(Customer currentUser){
        this.currentUser = currentUser;
    }

    public void run(Scanner scanner){

        runner:

        while(true) {
            String command = scanner.nextLine();
            Matcher matcher;
            if ((matcher = CustomerMenuCommands.getMatcher(command , CustomerMenuCommands.CHARGE_ACCOUNT)) != null) {
                int amount = Integer.parseInt(matcher.group("amount"));
                switch (CustomerMenuController.chargeCheck(amount)) {
                    case INVALID_AMOUNT:
                        System.out.println("charge account failed: invalid cost or price");
                        break;
                    case SUCCESS:
                        currentUser.increaseBalance(amount);
                        System.out.println("charge account successful");
                        break;
                }
            }
            else if (CustomerMenuCommands.getMatcher(command , CustomerMenuCommands.SHOW_BALANCE) != null)
                System.out.println(currentUser.getBalance());
            else if (CustomerMenuCommands.getMatcher(command , CustomerMenuCommands.SHOW_BOOKSTORES) != null){
                if(BookStore.isEmpty())
                    BookStore.nothing();
                else
                    System.out.println(BookStore.showBookStoresForCustomers());
            }
            else if ((matcher = CustomerMenuCommands.getMatcher(command , CustomerMenuCommands.SHOW_BOOKSTORES_WITH_TYPE)) != null){
                if (BookStore.isEmptyWithType(matcher.group("type")))
                    BookStore.nothing();
                else
                 System.out.println(BookStore.showBookStoresWithTypeForCustomers(matcher.group("type")));
            }
            else if ((matcher = CustomerMenuCommands.getMatcher(command , CustomerMenuCommands.SHOW_MENU)) != null){
                switch (CustomerMenuController.showMenuCheck(matcher)){
                    case BOOKSTORE_NOT_FOUND:
                        System.out.println("show menu failed: book store not found");
                        break;
                    case SUCCESS:
                        System.out.println(BookStore.getBookStoreByName(matcher.group("bookStoreName")).showMenu());
                        break;
                }
            }
            else if ((matcher = CustomerMenuCommands.getMatcher(command , CustomerMenuCommands.SHOW_MENU_WITH_GENRE)) != null) {
                switch (CustomerMenuController.showMenuWithCategoryCheck(matcher)) {
                    case BOOKSTORE_NOT_FOUND:
                        System.out.println("show menu failed: restaurant not found");
                        break;
                    case INVALID_CATEGORY:
                        System.out.println("show menu failed: invalid category");
                        break;
                    case SUCCESS:
                        if (BookStore.getBookStoreByName(matcher.group("bookStoreName")).menuIsEmptyForGenre(matcher.group("genre")))
                            BookStore.nothing();
                        else
                            System.out.println(BookStore.getBookStoreByName(matcher.group("bookStoreName")).showMenuForGenre(matcher.group("genre")));
                        break;
                }
            }
            else if((matcher = CustomerMenuCommands.getMatcher(command , CustomerMenuCommands.ADD_TO_CART)) != null){
                switch (CustomerMenuController.addToCartCheck(matcher)){
                    case BOOKSTORE_NOT_FOUND:
                        System.out.println("add to cart failed: book store not found");
                        break;
                    case BOOK_NOT_FOUND:
                        System.out.println("add to cart failed: book not found");
                        break;
                    case SUCCESS:
                        currentUser.addToCart(BookStore.getBookStoreByName(matcher.group("bookStoreName")).getBookByName(matcher.group("bookName")));
                        System.out.println("add to cart successful");
                        break;
                }
            }
            else if ((matcher = CustomerMenuCommands.getMatcher(command , CustomerMenuCommands.ADD_TO_CART_N)) != null){
                String bookStoreName = matcher.group("bookStoreName");
                String bookName = matcher.group("bookName");
                int number = Integer.parseInt(matcher.group("number"));
                switch (CustomerMenuController.addToCartNCheck(bookStoreName, bookName , number)){
                    case BOOKSTORE_NOT_FOUND:
                        System.out.println("add to cart failed: book store not found");
                        break;
                    case BOOK_NOT_FOUND:
                        System.out.println("add to cart failed: book not found");
                        break;
                    case INVALID_NUMBER:
                        System.out.println("add to cart failed: invalid number");
                        break;
                    case SUCCESS:
                        currentUser.addToCart(BookStore.getBookStoreByName(bookStoreName).getBookByName(bookName) , number);
                        System.out.println("add to cart successful");
                        break;
                }
            }
            else if ((matcher = CustomerMenuCommands.getMatcher(command , CustomerMenuCommands.REMOVE_FROM_CART)) != null){
                switch (CustomerMenuController.removeFromCartCheck(matcher , currentUser)){
                    case NOT_IN_CART:
                        System.out.println("remove from cart failed: not in cart");
                        break;
                    case SUCCESS:
                        currentUser.removeFromCart(BookStore.getBookStoreByName(matcher.group("bookStoretName")).getBookByName(matcher.group("bookName")));
                        System.out.println("remove from cart successful");
                        break;
                }
            }
            else if ((matcher = CustomerMenuCommands.getMatcher(command , CustomerMenuCommands.REMOVE_FROM_CART_N)) != null){
                String bookStoreName = matcher.group("bookStoreName");
                String bookName = matcher.group("bookName");
                int number = Integer.parseInt(matcher.group("number"));
                switch (CustomerMenuController.removeFromCartNCheck(bookStoreName , bookName, number , currentUser)){
                    case NOT_IN_CART:
                        System.out.println("remove from cart failed: not in cart");
                        break;
                    case INVALID_NUMBER:
                        System.out.println("remove from cart failed: invalid number");
                        break;
                    case NOT_ENOUGH_IN_CART:
                        System.out.println("remove from cart failed: not enough food in cart");
                        break;
                    case SUCCESS:
                        currentUser.removeFromCart(BookStore.getBookStoreByName(bookStoreName).getBookByName(bookName) , number);
                        System.out.println("remove from cart successful");
                        break;
                }
            }
            else if (CustomerMenuCommands.getMatcher(command , CustomerMenuCommands.SHOW_CART) != null) {
                if (currentUser.cartIsEmpty())
                    System.out.println("Total: 0");
                else
                    System.out.println(currentUser.showCart());
            }
            else if (CustomerMenuCommands.getMatcher(command , CustomerMenuCommands.SHOW_DISCOUNTS) != null) {
                if (currentUser.discountIsEmpty())
                    Customer.nothing();
                else
                    System.out.println(currentUser.showDiscounts());
            }
            else if (CustomerMenuCommands.getMatcher(command , CustomerMenuCommands.PURCHASE_CART) != null){
                switch (CustomerMenuController.purchaseCartCheck(currentUser)){
                    case INADEQUATE_MONEY:
                        System.out.println("purchase failed: inadequate money");
                        break;
                    case SUCCESS:
                        currentUser.purchaseCart();
                        System.out.println("purchase successful");
                        break;
                }
            }
            else if ((matcher = CustomerMenuCommands.getMatcher(command , CustomerMenuCommands.PURCHASE_CART_D)) != null){
                String code = matcher.group("discountCode");
                switch (CustomerMenuController.purchaseCartDCheck(currentUser , code)){
                    case INVALID_DISCOUNT_CODE:
                        System.out.println("purchase failed: invalid discount code");
                        break;
                    case INADEQUATE_MONEY:
                        System.out.println("purchase failed: inadequate money");
                        break;
                    case SUCCESS:
                        currentUser.purchaseCart(currentUser.getDiscountCodeByCode(code));
                        System.out.println("purchase successful");
                        break;
                }
            }
            else if (CustomerMenuCommands.getMatcher(command , CustomerMenuCommands.SHOW_CURRENT_MENU) != null)
                System.out.println("customer menu");
            else if (CustomerMenuCommands.getMatcher(command , CustomerMenuCommands.LOGOUT) != null)
                LoginMenu.run(scanner);
            else
                System.out.println("invalid command!");
        }

    }

}
