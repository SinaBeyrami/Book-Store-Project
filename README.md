# Book-Store Platform (Java • MVC • Console)

A role-based bookstore platform built with a clean **MVC** separation and a **regex-driven** command-line UI.  
Users can register and log in, **App Admins** onboard stores and issue discounts, **Store Admins** manage inventory and balance, and **Customers** browse, cart, apply discounts, and purchase.

<p align="center"><em>Java • MVC • Regex command routing • In-memory storage</em></p>

---

## Key Features

- **Three roles & permissions**
  - **App Admin**: create/remove book stores, list stores, assign discount codes, list all discounts
  - **Book Store Admin**: charge store account, add/remove books, view balance/menu
  - **Customer**: charge wallet, browse stores/menus (by type/genre), cart ops (add/remove/quantities), purchase (with/without discount)
- **Strict input validation** (usernames, passwords, genres, amounts, codes)
- **Deterministic business logic** in controllers; views only parse/print; models encapsulate state
- **Zero dependencies** (pure Java), quick to run and grade

---

## Project Structure

```

src/
├─ model/
│   ├─ User.java (base), Customer.java, BookStoreAdmin.java, AppAdmin.java
│   ├─ BookStore.java, Book.java, DiscountCode.java, Application.java
│
├─ controller/
│   ├─ LoginMenuController.java, MainMenuController.java
│   ├─ CustomerMenuController.java, BookStoreAdminMenuController.java, AppAdminMenuController.java
│
├─ view/
│   ├─ LoginMenu.java, MainMenu.java
│   ├─ CustomerMenu.java, BookStoreAdminMenu.java, AppAdminMenu.java
│   └─ enums/
│       ├─ commands/…  (regex grammars)
│       └─ messages/…  (typed controller results)
│
└─ Main.java            # entry point (creates App Admin from console input)
docs/
└─ uml.png              # class diagram (see below)

````

**UML (docs/uml.png)**  
Include the class diagram in `docs/uml.png` for quick architectural review.

---

## Build & Run

**Requirements:** Java 8+ (tested on 11/17)

```bash
# from the project root
find src -name "*.java" | xargs javac -d out
java -cp out Main
````

> On Windows (PowerShell):
>
> ```powershell
> Get-ChildItem -Recurse src -Filter *.java | % FullName | javac -d out @-
> java -cp out Main
> ```

At startup you’ll be prompted to create the **App Admin** (username/password).
All data is **in-memory** for the session (no external DB).

---

## Command Grammar (by menu)

> Commands are space-tolerant; tokens are single words unless noted.
> Amounts are **positive integers**. Genres are **Story | Science | Magazine**.

### 1) Login Menu

```
show current menu
register <username> <password>
login <username> <password>
change password <username> <oldPassword> <newPassword>
remove account <username> <password>
exit
```

**Username:** `[A-Za-z0-9_]+` and must include at least one letter
**Password:** `[A-Za-z0-9_]{5,}` and must contain ≥1 uppercase, ≥1 lowercase, ≥1 digit

---

### 2) Main Menu (after login)

```
show current menu
enter <customer menu | book store admin menu | App admin menu>
logout
```

Access is role-gated.

---

### 3) App Admin Menu

```
add book store <name> <password> <type>
show book store
show book store -t <type>
remove book store <name>
set discount <username> <amount> <code>
show discounts
logout
```

* `<type>`: lowercase descriptor of store category (e.g., `publisher`, `mall`).
* `set discount` attaches a code to a **Customer**.

---

### 4) Book Store Admin Menu

```
charge account <amount>
show balance
add book <name> <genre> <price> <cost>
remove book <name>
logout
```

* **Genre:** `Story | Science | Magazine`
* **Pricing model:** On purchase, store `balance += price` and `balance -= cost` for each sold book.

---

### 5) Customer Menu

```
charge account <amount>
show book store
show book store -t <type>
show menu <bookStoreName>
show menu <bookStoreName> -g <genre>
add to cart <bookStoreName> <bookName>
add to cart <bookStoreName> <bookName> -n <number>
remove from cart <bookStoreName> <bookName>
remove from cart <bookStoreName> <bookName> -n <number>
show cart
show discounts
purchase cart
purchase cart -d <discountCode>
logout
```

---

## End-to-End Example

```
# App Admin creates store
add book store alfaStore StrongP4ss publisher
show book store
# -> 1) alfaStore: type=publisher balance=0

# Store Admin session
enter book store admin menu
charge account 5000
add book foundations Science 300 200
add book dune Story 250 150
show balance
# -> 5000

# Customer session
register sina Passw0rd_
login sina Passw0rd_
enter customer menu
charge account 1000
show book store
show menu alfaStore
add to cart alfaStore dune -n 2
show cart
# -> 1) dune | book store=alfaStore price=500
#    Total: 500
purchase cart
# -> purchase successful
```

---

## Design Decisions & Invariants

* **MVC purity**

  * **View**: parses user input via regex enums and prints responses only.
  * **Controller**: validates inputs and enforces business rules (no I/O).
  * **Model**: encapsulates domain state (users, stores, menu, discounts, balances).
* **Validation rules**

  * Username/Password policies (see Login menu above)
  * Genres restricted to `Story | Science | Magazine`
  * All monetary fields and quantities must be `> 0`
  * Discount codes are alphanumeric
* **Cart semantics**

  * “Add/Remove” with `-n` adjusts multiplicity; default is 1
  * `show cart` aggregates same book and shows total
* **Accounting**

  * On purchase: `customer.balance -= sum(price)`, `store.balance += price - cost` per item
* **Storage**

  * In-memory static lists (simple, deterministic, test-friendly)

---

## Testing & Grading Aids

* **Happy-path scripts** for each role (see Example).
* **Edge cases to try**

  * Invalid usernames/passwords, weak new passwords
  * Non-existent store/book, invalid genre/type
  * Negative/zero amounts and quantities
  * Removing more items than present in cart
  * Purchase with inadequate funds; then retry after charging
  * Purchase with a discount that exactly meets affordability

---

## Extending the Project

* Persistence: swap in file/JSON persistence or a relational DB (JDBC/JPA)
* Authentication: hash passwords; add role claims
* Orders: maintain order history and receipts
* API/GUI: wrap controllers with REST (Spring) or a desktop GUI (JavaFX)
* Tests: JUnit tests for controllers and model invariants

---

## License

MIT © Sina Beyrami
