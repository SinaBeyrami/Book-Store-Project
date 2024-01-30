package model;

import java.util.ArrayList;

public class Customer extends User{

    private ArrayList<DiscountCode> discountCodes = new ArrayList<>();
    private int balance;
    private ArrayList<Book> cart = new ArrayList<>();

    public Customer(String username, String password) {
        super(username, password);
    }

    public int getBalance(){
        return this.balance;
    }

    public void increaseBalance(int amount){
        this.balance = this.balance + amount;
    }

    public void decreaseBalance(int amount){
        this.balance = this.balance - amount;
    }

    public ArrayList<DiscountCode> getDiscountCodes() {
        return discountCodes;
    }

    public void addToCart (Book book){
        this.cart.add(book);
    }

    public void addToCart (Book book , int number){
        for (int i = 0 ; i < number ; i++)
            this.cart.add(book);
    }

    public void removeFromCart (Book book){
        this.cart.remove(book);
    }

    public void removeFromCart (Book book , int number){
       while(number>0){
            for (int i = 0 ; i < this.cart.size() ; i++){
                if (this.cart.get(i).getName().equals(book.getName())){
                    this.cart.remove(this.cart.get(i));
                    number--;
                    break;
                }
            }
        }
/*        for (int i = 0 ; i < number ; i++)
            this.cart.remove(book); */
    }

    public Book getBookByNameFromCart(String bookName){
        for (Book book : this.cart){
            if (book.getName().equals(bookName))
                return book;
        }
        return null;
    }

    public int numberOfABookInCart(Book targetBook){
        int i = 0;
        for (Book book : this.cart){
            if ((targetBook.getName().equals(book.getName())) && (targetBook.getBookStore().getName().equals(book.getBookStore().getName())))
                i++;
        }
        return i;
    }

    public int totalPrice(){
        int sum = 0;
        for (Book book : this.cart){
            sum = sum + book.getPrice();
        }
        return sum;
    }

    public String showCart(){
        int i = 1;
        String str = "";
        for (int k = 0 ; k < this.cart.size() ; k++){
            int count = 0;
            for(int j = 0 ; j < k ; j++){
                if (this.cart.get(j).getName().equals(this.cart.get(k).getName())){
                    count++;
                }
            }
            if (count == 0){
                str = str + "\n" + i + ") " + this.cart.get(k).getName() + " | book store=" + this.cart.get(k).getBookStore().getName() + " price=" + (this.cart.get(k).getPrice()* numberOfABookInCart(this.cart.get(k)));
                i++;
            }
        }
        str = str + "\nTotal: " + this.totalPrice();
        return str.substring(1);
    }

    public boolean cartIsEmpty(){
        if (cart.size() == 0)
            return true;
        return false;
    }

    public static void nothing(){
        return;
    }

    public boolean discountIsEmpty(){
        if (this.discountCodes.size() == 0)
            return true;
        return false;
    }

    public String showDiscounts(){
        int i = 1;
        String str = "";
        for (DiscountCode discountCode : this.discountCodes){
            str = str + "\n" + i + ") " + discountCode.getCode() + " | amount=" + discountCode.getAmount();
            i++;
        }
        return str.substring(1);
    }

    public void purchaseCart(){
        for (Book book : this.cart){
            book.getBookStore().increaseBalance(book.getPrice());
            book.getBookStore().decreaseBalance(book.getCost());
            this.decreaseBalance(book.getPrice());
        }
        this.cart.clear();
    }

    public void purchaseCart(DiscountCode code){
        for (Book book : this.cart){
            book.getBookStore().increaseBalance(book.getPrice());
            book.getBookStore().decreaseBalance(book.getCost());
            this.decreaseBalance(book.getPrice());
        }
        this.increaseBalance(code.getAmount());
        this.cart.clear();
        this.discountCodes.remove(code);
        DiscountCode.removeCode(code);
    }

    public void addDiscountCode(DiscountCode discountCode){
        discountCodes.add(discountCode);
        discountCode.setOwner(this);
    }

    public DiscountCode getDiscountCodeByCode (String code){
        for (DiscountCode discountCode : this.discountCodes){
            if (discountCode.getCode().equals(code))
                return discountCode;
        }
        return null;
    }

}
