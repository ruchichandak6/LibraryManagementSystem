public class Utils {

    public static void bubbleSortByTitle(Book[] books, int count) {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (books[j].title.compareToIgnoreCase(books[j + 1].title) > 0) {
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
        System.out.println("✅ Sorted by title (Bubble Sort)");
    }

    public static void insertionSortByAuthor(Book[] books, int count) {
        for (int i = 1; i < count; i++) {
            Book key = books[i];
            int j = i - 1;
            while (j >= 0 && books[j].author.compareToIgnoreCase(key.author) > 0) {
                books[j + 1] = books[j];
                j--;
            }
            books[j + 1] = key;
        }
        System.out.println("✅ Sorted by author (Insertion Sort)");
    }

    public static void insertionSortById(Book[] books, int count) {
        for (int i = 1; i < count; i++) {
            Book key = books[i];
            int j = i - 1;
            while (j >= 0 && books[j].id > key.id) {
                books[j + 1] = books[j];
                j--;
            }
            books[j + 1] = key;
        }
    }

    public static int binarySearchByTitle(Book[] books, int count, String title) {
        int low = 0, high = count - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = books[mid].title.compareToIgnoreCase(title);
            if (cmp == 0) return mid;
            if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public static int binarySearchById(Book[] books, int count, int id) {
        int low = 0, high = count - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (books[mid].id == id) return mid;
            if (books[mid].id < id) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public static Book getMostBorrowedBook(Book[] books, int count) {
        Book max = null;
        for (int i = 0; i < count; i++) {
            if (books[i] != null && (max == null || books[i].borrowCount > max.borrowCount)) {
                max = books[i];
            }
        }
        return max;
    }
}
