package model;

import java.util.ArrayList;

public class User {

    private final String username;
    private static ArrayList<User> allUsers = new ArrayList<>();
    private String password;
    protected String accessMenu;

    public User(String username , String password){
        this.username = username;
        this.password = password;
        User.addUser(this);
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }
    public static void addUser(User user){
        allUsers.add(user);
    }

    public static void deleteUser(User user){
        allUsers.remove(user);
    }

    public static User getUserByUsername(String username){
        for (User user : allUsers){
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public void changePassword (String newPassword){
        this.password = newPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
