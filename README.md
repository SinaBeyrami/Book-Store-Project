# Book-Store-Project

This project follows *MVC* **architecture**

model (**M**):
This package is for saving data and main classes.
We have User, Book, BookStore, Customer, BookStoreAdmin, Application, DiscountCode, AppAdmin classes in this package.

view (**V**):
This package somehow performs the work of the interface and is for the classes that the user deals with.
We have commands and messages enums and MainMenu, LoginMenu, CustomerMenu, BookStoreAdminMenu, AppAdminMenu classes in this package.

controller (**C**):
This package is the interface between the model and the view, or in a way, the connection between the main core and the logic of the program.
We have MainMenuController, LoginMenuController, CustomerMenuController, BookStoreAdminMenuController, AppAdminMenuController classes in this package.


Details of how the program works:
At the beginning of the program, we are in the **login menu**.
In this menu, it is possible to *create a new account*, *change the password*, *delete the account* and *login*.
After logging in, we enter the **main menu**.
From this menu, we can *enter* one of the **customer menu** or **book store admin menu** or **App admin menu** or *log out* and return to the **login menu**.
If an invalid command is entered in any menu, the statement *invalid command* will be displayed.
In any menu, by entering the command *"show current menu"*, the name of the menu will be displayed.
In addition to the main menu, the *"logout"* command *is valid* in other menus (obviously, *except the registration menu*).

