# Book-Store-Project

This project follows *MVC* **architecture**.

model (**M**):
This package is for saving data and main classes.
We have User, Book, BookStore, Customer, BookStoreAdmin, Application, DiscountCode, AppAdmin classes in this package.

view (**V**):
This package somehow performs the work of the interface and is for the classes that the user deals with.
We have commands and messages enums and MainMenu, LoginMenu, CustomerMenu, BookStoreAdminMenu, AppAdminMenu classes in this package.

controller (**C**):
This package is the interface between the model and the view, or in a way, the connection between the main core and the logic of the program.
We have MainMenuController, LoginMenuController, CustomerMenuController, BookStoreAdminMenuController, AppAdminMenuController classes in this package.
.

.

.

.

.

**Details of how the program works**:

At the beginning of the program, we are in the **login menu**.
In this menu, it is possible to *create a new account*, *change the password*, *delete the account* and *login*.
After logging in, we enter the **main menu**.
From this menu, we can *enter* one of the **customer menu** or **book store admin menu** or **App admin menu** or *log out* and return to the **login menu**.
If an invalid command is entered in any menu, the statement *invalid command* will be displayed.
In any menu, by entering the command *"show current menu"*, the name of the menu will be displayed.
In addition to the main menu, the *"logout"* command *is valid* in other menus (obviously, *except the registration menu*).

.

.

**login Menu commands**:

.

*register <username> <password>*:

Username can only contain numbers, English alphabets and underscore characters. It must also contain at least one letter of the English alphabet.

User's passwords must be strong, which means they must have at least 5 characters and it must have an uppercase English letter, a lowercase English letter and a number, and at least one character of each must be in the password.

.

*login <usernam> <password>*:

This command is given for user login with username and password.

.

*change password <username> <old password> <new password>*:

Old password must be correct and new password must be strong.

.

*remove account <username> <password>*:

.

*exit*:

.

.

**Main Menu commands**:

.

*enter <menu name>*:

By this command, we can enter one of three menu. (customer menu, book store admin menu, App admin menu)
According to his/her role, each user can only enter one of the three mentioned menus.

.

*logout*:

With this command, the user is logged out and returns to the login menu.

.

.

**App admin Menu commands**:

.

*add book store <name> <password> <type>*:

By entering this command, a new book store will be created with the specified name and type and zero balance.

.

*show book store -t <type>*:

With this command, the App admin can view the list of all book stores. By entering the <type> option, the admin can see only book stores that have a certain type (for example publisher or mall).

.

*remove restaurant <name>*:

Whit this command, a book store will be removed.

.

*set discount <username> <amount> <code>*:

The App admin can assign a discount with a specified amount and code to a customer using this command.

.

*show discounts*:

By typing this command, the list of all discount codes assigned to customers will be displayed.

.

.

**Book store admin Menu commands**:

.

.

*charge account <amount>*:

This command is used to increase the balance of the book store.

.

*show balance*:

With this command, the balance of the book store account will be displayed.

.

*add book <name> <genre> <price> <cost>*:

By entering this command, the book store admin can enter a book with the specified genre, price and purchase or production cost into the menu.

(genre choises in this program: Story, Science, Magazine)

.

*remove book <name>*:

This command deletes a book with the specified name.

.

.

**Customer Menu Commands**:

.

*show book store -t <type>*:

This command has the same functionality as the show restaurant command in the App admin menu, with the difference that logically a customer cannot see the account balance of a book store.

.

*show menu <book store name> -g <genre>*:

If the command is executed without genre option, it will display the list of all the books in the book store menu.

If the command is used with -g option, only books of that genre will be displayed.

.

*add to cart <book store name> <book name> -n <number>*:

This command adds the specified number of a book (default = 1) to the cart.

.

*remove from cart <book store name> <book name> -n <number>*:

This command removes a number (default = 1) of a book from the shopping cart.

.

*show cart*:

This command displays the books in the customer's shopping cart along with the total price.

.

*show discounts*:

With this command, the customer can see all the discount codes assigned to his/her by the App admin.

.

*purchase cart -d <discount code>*:

With this command, the customer can finalize his/her order by paying the total price of the books in the shopping cart.

Customer can also use one of the discount codes by using option -d.

.

.
