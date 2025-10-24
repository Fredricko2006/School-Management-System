import java.util.*;

/*
 LibrarySystem
 - Catalog stored in a HashMap keyed by ISBN for fast lookup.
 - A Stack records recent actions (BORROW/RETURN) in LIFO order for quick access to recent events.
*/
public class LibrarySystem {
    public static class Book {
        public String isbn;
        public String title;
        public int copies;
        public Book(String isbn, String title, int copies) {
            this.isbn = isbn;
            this.title = title;
            this.copies = copies;
        }
        public String toString() {
            return isbn + " | " + title + " | copies:" + copies;
        }
    }

    public static class Action {
        public String isbn;
        public String studentId;
        public String type;
        public long time;
        public Action(String isbn, String studentId, String type) {
            this.isbn = isbn;
            this.studentId = studentId;
            this.type = type;
            this.time = System.currentTimeMillis();
        }
        public String toString() {
            return type + " | " + isbn + " | " + studentId + " | " + new Date(time);
        }
    }

    private Map<String, Book> catalog = new HashMap<>();
    private Stack<Action> history = new Stack<>();

    // Add or update a book in the catalog.
    public void addBook(Book b) { catalog.put(b.isbn, b); }

    // Borrow a book if copies available; record action on stack.
    public boolean borrow(String isbn, String studentId) {
        Book b = catalog.get(isbn);
        if (b == null || b.copies <= 0) return false;
        b.copies--;
        history.push(new Action(isbn, studentId, "BORROW"));
        return true;
    }

    // Return a book; record action on stack.
    public boolean returnBook(String isbn, String studentId) {
        Book b = catalog.get(isbn);
        if (b == null) return false;
        b.copies++;
        history.push(new Action(isbn, studentId, "RETURN"));
        return true;
    }

    // Lookup a book by ISBN.
    public Book lookup(String isbn) { return catalog.get(isbn); }

    // Get recent actions up to n (most recent first).
    public List<Action> recentActions(int n) {
        List<Action> out = new ArrayList<>();
        for (int i = history.size() - 1; i >= 0 && out.size() < n; i--) out.add(history.get(i));
        return out;
    }

    // Demo main
    public static void main(String[] args) {
        LibrarySystem lib = new LibrarySystem();
        lib.addBook(new Book("978-1", "Intro to DS", 2));
        System.out.println(lib.borrow("978-1", "S001"));
        System.out.println(lib.lookup("978-1"));
        System.out.println(lib.returnBook("978-1", "S001"));
        System.out.println("Recent: " + lib.recentActions(5));
    }
}

