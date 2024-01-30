# Book-Store-Project

This project follows **MVC architecture**

model (M):
This package is for saving data and main classes.
We have User, Book, BookStore, Customer, BookStoreAdmin, Application, DiscountCode, AppAdmin classes in this package.

view (V):
This package somehow performs the work of the interface and is for the classes that the user deals with.
We have commands and messages enums and MainMenu, LoginMenu, CustomerMenu, BookStoreAdminMenu, AppAdminMenu classes in this package.

controller (C):
This package is the interface between the model and the view, or in a way, the connection between the main core and the logic of the program.
We have MainMenuController, LoginMenuController, CustomerMenuController, BookStoreAdminMenuController, AppAdminMenuController classes in this package.

