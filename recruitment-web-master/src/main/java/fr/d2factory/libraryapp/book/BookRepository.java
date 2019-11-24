package fr.d2factory.libraryapp.book;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The book repository emulates a database via 2 HashMaps
 */
public class BookRepository {
    public static Map<ISBN, Book> availableBooks;
    public static  Map<Book, LocalDate> borrowedBooks ;
    
    /**
     * Constructor to initialize  2 HashMaps
     */
 public  BookRepository() {
	 availableBooks = new HashMap<>();
	 borrowedBooks = new HashMap<>();
    }



    public static void addBooks(List<Book> books){

    	for (int i=0; i < books.size(); i++) {
    		
    	 availableBooks.put(books.get(i).getIsbn(), books.get(i));
        }
     
    }
    
    public static void ReturnBook(Book book){
    	borrowedBooks.remove(book);
   	    availableBooks.put(book.getIsbn(), book);
    }
    
    public Book  findBook(ISBN isbn) {
    		
       return availableBooks.get(isbn);
    }

    public static void saveBookBorrow(Book book, LocalDate borrowedAt){
    	borrowedBooks.put(book, borrowedAt);
    	availableBooks.remove((book).getIsbn());
    }

    public  LocalDate findBorrowedBookDate(Book book) {
    	if(borrowedBooks.containsKey(book)) {
    	return borrowedBooks.get(book);
    	}
    	
    	return null;    
    }
}
