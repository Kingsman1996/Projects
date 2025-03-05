import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Member {
    private String id;
    private String name;
    private List<Book> borrowedBooks;

    public Member() {
    }

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book newBook) {
        borrowedBooks.add(newBook);
    }

    public void returnBook(String bookTitle) {
        Iterator<Book> iterator = borrowedBooks.iterator();
        while (iterator.hasNext()) {
            Book item = iterator.next();
            if (item.getTitle().equals(bookTitle)) {
                item.setBorrowed(false);
                item.setWhoBorrowed(null);
                iterator.remove();
                return;
            }
        }
        throw new NoSuchElementException(name + " has not borrowed " + bookTitle);
    }

    @Override
    public String toString() {
        return "\nMember: "
                + "ID: " + id
                + ", Name: " + name
                + ", Has borrowed: " + !borrowedBooks.isEmpty();
    }
}
