import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        Member member1 = new Member("m1", "Zed");
        Member member2 = new Member("m2", "Yasuo");
        Member member3 = new Member("m3", "Garen");
        Book book1 = new Book("b1", "Chinh Phuc 1", "LaiH");
        Book book2 = new Book("b2", "Chinh Phuc 2", "LaiH");
        Book book3 = new Book("b3", "Chinh Phuc 3", "LaiH");
        Library lib = new Library();
        lib.addBook(book1);
        lib.addBook(book2);
        lib.addBook(book3);
        lib.addMember(member1);
        lib.addMember(member2);
        lib.addMember(member3);
        try {
            lib.setBookToMember("b1", "m1");
            System.out.println(lib.toString());
            member1.returnBook("Chinh Phuc 1");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(lib.toString());
    }
}
