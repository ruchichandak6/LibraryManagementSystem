public class BorrowList {
    private class Node {
        BorrowRecord record;
        Node next;

        Node(BorrowRecord record) {
            this.record = record;
        }
    }

    private Node head;

    public void addRecord(BorrowRecord record) {
        Node newNode = new Node(record);
        newNode.next = head;
        head = newNode;
    }

    public void removeRecord(int bookId, String userName) {
        Node curr = head, prev = null;
        while (curr != null) {
            if (curr.record.bookId == bookId && curr.record.userName.equalsIgnoreCase(userName)) {
                if (prev == null) {
                    head = curr.next;
                } else {
                    prev.next = curr.next;
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public void displayHistory() {
        Node curr = head;
        if (curr == null) {
            System.out.println("No borrow history yet.");
            return;
        }
        while (curr != null) {
            curr.record.display();
            curr = curr.next;
        }
    }
}
