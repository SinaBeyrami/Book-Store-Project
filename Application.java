package model;

import java.util.ArrayList;

public class Application {

    private static User appAdmin;

    public static void setAppAdmin (AppAdmin user){
        appAdmin = user;
    }

    public static User getAppAdmin() {
        return appAdmin;
    }

}
