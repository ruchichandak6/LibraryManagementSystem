import java.util.Scanner;
import java.time.LocalDate;

public class LibraryApp {
    static Book[] books = new Book[100];
    static int bookCount = 0;

    static User[] users = new User[50];
    static int userCount = 0;

    static BorrowList borrowList = new BorrowList();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n📚 LIBRARY MENU:");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Borrow History");
            System.out.println("6. Sort Books");
            System.out.println("7. Search Book");
            System.out.println("8. Most Borrowed Book");
            System.out.println("9. Display Users");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: addBook(sc); break;
                case 2: displayBooks(); break;
                case 3: borrowBook(sc); break;
                case 4: returnBook(sc); break;
                case 5: viewBorrowHistory(); break;
                case 6: sortBooks(sc); break;
                case 7: searchBook(sc); break;
                case 8: displayMostBorrowed(); break;
                case 9: displayUsers(); break;
                case 0:
                    System.out.println("👋 Exiting...");
                    return;
                default:
                    System.out.println("❌ Invalid option!");
            }
        }
    }

    static void addBook(Scanner sc) {
        if (bookCount >= books.length) {
            System.out.println("❌ Cannot add more books. Library is full.");
            return;
        }

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (findBookById(id) != null) {
            System.out.println("❌ Book ID already exists!");
            return;
        }

        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        if (title.isEmpty() || author.isEmpty() || category.isEmpty()) {
            System.out.println("❌ Fields cannot be empty.");
            return;
        }

        books[bookCount++] = new Book(id, title, author, category);
        System.out.println("✅ Book added successfully!");
    }

    static void displayBooks() {
        if (bookCount == 0) {
            System.out.println("No books added yet.");
            return;
        }
        System.out.println("\n📖 BOOK LIST:");
        for (int i = 0; i < bookCount; i++) {
            books[i].display();
        }
    }

    static void borrowBook(Scanner sc) {
        if (bookCount == 0) {
            System.out.println("❌ No books in library.");
            return;
        }

        System.out.print("Enter Book ID to borrow: ");
        int id = sc.nextInt();
        sc.nextLine();
        Book b = findBookById(id);

        if (b == null) {
            System.out.println("❌ Book not found.");
            return;
        }
        if (!b.isAvailable) {
            System.out.println("❌ Book is already borrowed.");
            return;
        }

        System.out.print("Enter your name: ");
        String userName = sc.nextLine().trim();
        if (userName.isEmpty()) {
            System.out.println("❌ Name cannot be empty.");
            return;
        }

        if (userCount >= users.length) {
            System.out.println("❌ Cannot register more users.");
            return;
        }

        User user = findUser(userName);
        if (user == null) {
            users[userCount++] = new User(userName);
        } else {
            user.incrementBorrow();
        }

        b.isAvailable = false;
        b.borrowCount++;
        String date = LocalDate.now().toString();
        borrowList.addRecord(new BorrowRecord(id, userName, date));
        System.out.println("✅ Book borrowed successfully!");
    }

    static void returnBook(Scanner sc) {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();
        sc.nextLine();
        Book b = findBookById(id);

        if (b == null || b.isAvailable) {
            System.out.println("❌ Invalid return.");
            return;
        }

        System.out.print("Enter your name: ");
        String userName = sc.nextLine().trim();
        if (userName.isEmpty()) {
            System.out.println("❌ Name cannot be empty.");
            return;
        }

        borrowList.removeRecord(id, userName);
        b.isAvailable = true;
        System.out.println("✅ Book returned successfully.");
    }

    static void viewBorrowHistory() {
        System.out.println("📚 Borrow History:");
        borrowList.displayHistory();
    }

    static void sortBooks(Scanner sc) {
    if (bookCount == 0) {
        System.out.println("❌ No books to sort.");
        return;
    }

    System.out.println("Sort by:\n1. Title\n2. Author");
    int opt = sc.nextInt();
    sc.nextLine();

    if (opt == 1) {
        Utils.bubbleSortByTitle(books, bookCount);
        displayBooks(); // ✅ Display after sorting by title
    } else if (opt == 2) {
        Utils.insertionSortByAuthor(books, bookCount);
        displayBooks(); // ✅ Display after sorting by author
    } else {
        System.out.println("❌ Invalid choice.");
    }
}


    static void searchBook(Scanner sc) {
        if (bookCount == 0) {
            System.out.println("❌ No books to search.");
            return;
        }

        System.out.println("Search by:\n1. ID\n2. Title");
        int opt = sc.nextInt();
        sc.nextLine();

        Utils.insertionSortById(books, bookCount); // Binary search needs sorted array

        if (opt == 1) {
            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();
            int idx = Utils.binarySearchById(books, bookCount, id);
            if (idx != -1) books[idx].display();
            else System.out.println("❌ Book not found.");
        } else if (opt == 2) {
            System.out.print("Enter Title: ");
            String title = sc.nextLine();
            int idx = Utils.binarySearchByTitle(books, bookCount, title);
            if (idx != -1) books[idx].display();
            else System.out.println("❌ Book not found.");
        } else {
            System.out.println("❌ Invalid choice.");
        }
    }

    static void displayMostBorrowed() {
        if (bookCount == 0) {
            System.out.println("❌ No books available.");
            return;
        }
        Book most = Utils.getMostBorrowedBook(books, bookCount);
        if (most != null) {
            System.out.println("📘 Most Borrowed Book:");
            most.display();
        } else {
            System.out.println("❌ No borrow records found.");
        }
    }

    static void displayUsers() {
        if (userCount == 0) {
            System.out.println("No users found.");
            return;
        }
        System.out.println("📋 User List:");
        for (int i = 0; i < userCount; i++) {
            users[i].display();
        }
    }

    static Book findBookById(int id) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].id == id) return books[i];
        }
        return null;
    }

    static User findUser(String name) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].name.equalsIgnoreCase(name)) return users[i];
        }
        return null;
    }
}
