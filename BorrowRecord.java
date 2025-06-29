public class BorrowRecord {
    int bookId;
    String userName;
    String date;

    public BorrowRecord(int bookId, String userName, String date) {
        this.bookId = bookId;
        this.userName = userName;
        this.date = date;
    }

    public void display() {
        System.out.println("User: " + userName + ", Book ID: " + bookId + ", Date: " + date);
    }
}
