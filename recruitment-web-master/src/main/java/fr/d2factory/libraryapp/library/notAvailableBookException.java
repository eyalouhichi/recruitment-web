package fr.d2factory.libraryapp.library;

public class notAvailableBookException extends Exception{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public notAvailableBookException() {
	System.out.println("the book is not available");
	}
}
