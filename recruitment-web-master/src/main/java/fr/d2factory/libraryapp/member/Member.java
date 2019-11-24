package fr.d2factory.libraryapp.member;

import java.util.ArrayList;
import java.util.List;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.library.ILibrary;

/**
 * A member is a person who can borrow and return books to a {@link ILibrary}
 * A member can be either a student or a resident
 */
public abstract class Member {
    /**
     * An initial sum of money the member has
     */
    private float wallet;
    private List<Book> books;
    private String name;
    /**
     * The member should pay their books when they are returned to the library
     *
     * @param numberOfDays the number of days they kept the book
     */
    public abstract void payBook(int numberOfDays);
    

    public float getWallet() {
        return wallet;
    }

    public void setWallet(float wallet) {
        this.wallet -= wallet;
    }

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

public void addBookToList(Book book) {
	books.add(book);
}


public Member(float wallet, String name) {
	super();
	books =new ArrayList<Book>();
	this.wallet = wallet;
	this.name = name;
}


}