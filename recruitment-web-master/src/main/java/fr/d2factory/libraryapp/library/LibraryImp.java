package fr.d2factory.libraryapp.library;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.book.ISBN;
import fr.d2factory.libraryapp.member.Member;
import fr.d2factory.libraryapp.member.Student;

public class LibraryImp implements ILibrary{
	BookRepository rep = new BookRepository();
	@Override
	public void borrowBook(ISBN isbn, Member member, LocalDate borrowedAt) throws HasLateBooksException , notAvailableBookException {
		
		if (isLate(member)==true) {throw new HasLateBooksException();}
		else {
			Book borrowBook =rep.findBook(isbn);

	          if(borrowBook == null) {
				throw new notAvailableBookException();
			}
	           else {
	        	   
	               BookRepository.saveBookBorrow(borrowBook, borrowedAt);
	               member.addBookToList(borrowBook);
		}
	           
		}
	}

	@Override
	public void returnBook(Book book, Member member) {
		member.payBook( numberOfDays (book));	
		BookRepository.ReturnBook(book);

	}
	 /**
     * numberOfDays the number of days they kept the book
     *
     * @param the book which we should compute the number of days
     */
    public int numberOfDays(Book book) {
	  int numberOfDays;
	  LocalDate date= LocalDate.now(); 
	  int year =  date.getYear() - rep.findBorrowedBookDate(book).getYear();
	  int	month = date.getMonthValue() - rep.findBorrowedBookDate(book).getMonthValue();
	  int day = date.getDayOfMonth()- rep.findBorrowedBookDate(book).getDayOfMonth();
	  numberOfDays = year * 360 + month * 30 + day;
	return numberOfDays;
}
    /**
     * check if the member is late or not
     *
     * @param the book which we should compute the number of days
     */  
public Boolean isLate(Member member) {
	int maxDays=60;
	List<Book> books = member.getBooks();
	if (member instanceof Student) {
	maxDays =30;}
	if (books!=null) {
		for(int i=0;i<books.size();i++) {
			if(numberOfDays(books.get(i))>maxDays){
				return true;
		}
	}
	}
	
	return false;
	
}
}
