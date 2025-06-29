public class Book {
    int id;
    String title;
    String author;
    String category;
    boolean isAvailable = true;
    int borrowCount = 0;

    public Book(int id, String title, String author, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public void display() {
        System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author + ", Category: " + category + ", Available: " + (isAvailable ? "Yes" : "No"));
    }
}
