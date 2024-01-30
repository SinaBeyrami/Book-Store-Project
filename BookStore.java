package model;

import java.util.ArrayList;

public class BookStore {

    private static ArrayList<BookStore> allBookStores = new ArrayList<>();
    private String name;
    private String username = name;
    private String password;
    private String type;
    private ArrayList<Book> menu = new ArrayList<>();
    private int balance = 0;

    public static ArrayList<BookStore> getAllBookStores() {
        return allBookStores;
    }

    public static void addBookStore(BookStore bookStore){
        allBookStores.add(bookStore);
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public int getBalance() {
        return balance;
    }

    public static String showBookStores(){
        String str = " ";
        for (BookStore bookStore : allBookStores){
            str = str + (allBookStores.indexOf(bookStore)+1) + ") " + bookStore.getName() + ": type=" + bookStore.getType() + " balance=" + bookStore.getBalance();
            if (allBookStores.indexOf(bookStore) != (allBookStores.size()-1))
                str = str + "\n";
        }
        return str.substring(1);
    }

    public static boolean isEmptyWithType(String type){
        int i = 0;
        for (BookStore bookStore : allBookStores){
            if (bookStore.getType().equals(type))
                i++;
        }
        if (i == 0)
            return true;
        return false;
    }

    public static String showBookStoresWithType(String type){
        String str = " ";
        int i = 1;
        for (BookStore bookStore : allBookStores){
            if (bookStore.getType().equals(type)){
                str = str + i + ") " + bookStore.getName() + ": type=" + bookStore.getType() + " balance=" + bookStore.getBalance() + "\n";
                i++;
            }
        }
        return str.substring(1 , str.length()-1);
    }

    public static String showBookStoresForCustomers(){
        String str = " ";
        for (BookStore bookStore : allBookStores){
            str = str + (allBookStores.indexOf(bookStore)+1) + ") " + bookStore.getName() + ": type=" + bookStore.getType();
            if (allBookStores.indexOf(bookStore) != (allBookStores.size()-1))
                str = str + "\n";
        }
        return str.substring(1);
    }



    public static String showBookStoresWithTypeForCustomers(String type){
        String str = "";
        int i = 1;
        for (BookStore bookStore : allBookStores){
            if (bookStore.getType().equals(type)){
                str = str  + "\n" + i + ") " + bookStore.getName() + ": type=" + bookStore.getType();
                i++;
            }
        }
        return str.substring(1);
    }

    public BookStore(String name , String password , String type){
        this.name = name;
        this.password = password;
        this.type = type;
        allBookStores.add(this);
    }

    public static BookStore getBookStoreByName(String name){
        for (BookStore bookStore : allBookStores){
            if (bookStore.name.equals(name))
                return bookStore;
        }
        return null;
    }

    public void increaseBalance(int amount){
        this.balance = this.balance + amount;
    }

    public void decreaseBalance(int amount){
        this.balance = this.balance - amount;
    }

    public static void removeBookStore(BookStore bookStore){
        allBookStores.remove(bookStore);
    }

    public static boolean isEmpty() {
        if (allBookStores.size() == 0)
            return true;
        else
            return false;
    }

    public static void nothing(){
        return;
    }

    public void addBook(Book book){
        this.menu.add(book);
    }

    public boolean hasBook(String bookName){
        for (Book book : this.menu){
            if (book.getName().equals(bookName))
                return true;
        }
        return false;
    }

//    public void removeBookByName(String name){
//        for (Book book : this.list){
//            if (book.getName().equals(name)){
//                this.list.remove(book);
//            }
//        }
//    }

    public void removeBookByName(String name){
        for (int i = 0 ; i < this.menu.size() ; i++){
            if (this.menu.get(i).getName().equals(name)){
                this.menu.remove(this.menu.get(i));
            }
        }
    }

    public String showMenu(){
        String stories = "<< Story >>";
        String sciences = "<< Science >>";
        String magazines = "<< Magazine >>";
        for (Book book : this.menu){
            if (book.getGenre().equals("Story")){
                stories = stories + "\n" + book.getName() + " | price=" + book.getPrice();
            }
            else if (book.getGenre().equals("Science")){
                sciences = sciences + "\n" + book.getName() + " | price=" + book.getPrice();
            }
            else if (book.getGenre().equals("Magazine")){
                magazines = magazines + "\n" + book.getName() + " | price=" + book.getPrice();
            }
        }
        String finalString = stories + "\n" + sciences + "\n" + magazines;
        return finalString;
    }

    public String showMenuForGenre(String genre){
        String str = " ";
        for (Book book : this.menu){
            if (book.getGenre().equals(genre)){
                str = str + book.getName() + " | price=" + book.getPrice() + "\n";
            }
        }
        return str.substring(1 , str.length()-1);
    }

    public boolean menuIsEmptyForGenre(String genre){
        int i = 0;
        for (Book book : this.menu){
            if (book.getGenre().equals(genre))
                i++;
        }
        if (i == 0)
            return true;
        return false;
    }

    public Book getBookByName(String bookName){
        for (Book book : this.menu){
            if (book.getName().equals(bookName))
                return book;
        }
        return null;
    }

}
