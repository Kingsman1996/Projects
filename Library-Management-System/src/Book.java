public class Book {
    private String id;
    private String title;
    private String author;
    private boolean isBorrowed;
    private String whoBorrowed;

    public Book() {
    }

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public String getWhoBorrowed() {
        return whoBorrowed;
    }

    public void setWhoBorrowed(String whoBorrowed) {
        this.whoBorrowed = whoBorrowed;
    }

    @Override
    public String toString() {
        return "\nBook: "
                + "ID: " + id
                + ", Title: " + title
                + ", Author: " + author
                + ", Borrowed by: " + whoBorrowed;
    }
}
