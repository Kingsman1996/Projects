import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Library {
    private List<Book> allBooks;
    private int bookAmount;
    private List<Member> allMembers;
    private int memberAmount;

    public Library() {
        allBooks = new ArrayList<>();
        bookAmount = 0;
        allMembers = new ArrayList<>();
        memberAmount = 0;
    }

    public void addBook(Book newBook) {
        allBooks.add(newBook);
        bookAmount++;
    }

    public void addMember(Member newMember) {
        allMembers.add(newMember);
        memberAmount++;
    }

    // Sửa, xóa book
    // Sửa, xóa member
    // Quản lý mượn trả
    public Member getMemberById(String id) {
        boolean found = false;
        Member foundMember = null;
        for (Member item : allMembers) {
            if (item.getId().equals(id)) {
                found = true;
                foundMember = item;
            }
        }
        if (!found) {
            throw new NoSuchElementException("No member with id " + id);
        } else {
            return foundMember;
        }
    }

    public List<Member> getAllMembers() {
        return allMembers;
    }

    public Book getBookById(String id) {
        boolean found = false;
        Book foundBook = null;
        for (Book item : allBooks) {
            if (item.getId().equals(id)) {
                found = true;
                foundBook = item;
            }
        }
        if (!found) {
            throw new NoSuchElementException("No book with id " + id);
        } else {
            return foundBook;
        }
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public void setBookToMember(String bookId, String memberId) {
        Book tempBook = null;
        Member tempMember = null;
        tempBook = getBookById(bookId);
        tempBook.setBorrowed(true);
        tempMember = getMemberById(memberId);
        tempMember.borrowBook(tempBook);
        tempBook.setWhoBorrowed(tempMember.getName());
        if (tempBook == null || tempMember == null) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public String toString() {
        return "Member list: " + allMembers
                + "\nMember Amount: " + memberAmount
                + "\nBooks list: " + allBooks
                + "\nBook Amount: " + bookAmount;
    }
}
