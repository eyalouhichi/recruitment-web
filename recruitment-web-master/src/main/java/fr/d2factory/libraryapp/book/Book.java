package fr.d2factory.libraryapp.book;

/**
 * A simple representation of a book
 */
public class Book {
    public String title;
    String author;
    private ISBN isbn;

    public Book() {		
	}

	public Book(String title, String author, ISBN isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

	public ISBN getIsbn() {
		return isbn;
	}

	public void setIsbn(ISBN isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", isbn=" + isbn + "]";
	}
	
}
