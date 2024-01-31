import model.AppAdmin;
import view.LoginMenu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Please enter App admin username:");
        Scanner scanner = new Scanner(System.in);
        String AppAdminUsername = scanner.next().trim();
        scanner.nextLine();
        System.out.println("Please enter App admin password:");
        String AppAdminPassword = scanner.next().trim();
        scanner.nextLine();
        AppAdmin appAdmin = new AppAdmin(AppAdminUsername , AppAdminPassword);
        LoginMenu.run(scanner);
    }

}
