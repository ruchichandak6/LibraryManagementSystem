public class User {
    String name;
    int borrowCount;

    public User(String name) {
        this.name = name;
        this.borrowCount = 1;
    }

    public void incrementBorrow() {
        borrowCount++;
    }

    public void display() {
        System.out.println("User: " + name + ", Books Borrowed: " + borrowCount);
    }
}
