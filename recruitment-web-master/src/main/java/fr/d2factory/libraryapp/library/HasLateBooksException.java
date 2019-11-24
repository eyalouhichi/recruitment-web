package fr.d2factory.libraryapp.library;

/**
 * This exception is thrown when a member who owns late books tries to borrow another book
 */
public class HasLateBooksException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HasLateBooksException() {
		System.out.println("you have some books in late. you can't borrow more.");
		
	}
	
}
