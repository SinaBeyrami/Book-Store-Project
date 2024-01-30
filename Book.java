package model;

public class Book {

    private String name;
    private String genre;
    private int price;
    private int cost;
    private BookStore bookStore;

    public Book(String name, String genre, int price, int cost , BookStore bookStore) {
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.cost = cost;
        this.bookStore = bookStore;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getPrice() {
        return price;
    }

    public int getCost() {
        return cost;
    }

    public BookStore getBookStore() {
        return bookStore;
    }
}
