package fr.d2factory.libraryapp.library;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.book.ISBN;
import fr.d2factory.libraryapp.member.Member;
import fr.d2factory.libraryapp.member.Resident;
import fr.d2factory.libraryapp.member.Student;


import org.junit.BeforeClass;
import org.junit.Test;



import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class LibraryTest {
    private static ILibrary library ;
    static BookRepository bookRepository = new BookRepository();
    static Book bookHarry;
    static Book bookAround;
    static Book bookPeau;
    static Book bookCatch;
    static List<Book> booksTest = new  ArrayList<Book>();

    @BeforeClass
    public static void setup(){
        //TODO instantiate the library and the repository
    	library = new LibraryImp();
        //TODO add some test books (use BookRepository#addBooks)
    

    	
    	  bookHarry = new Book("Harry Potter", "J.K. Rowling",new ISBN (Long.parseLong("46578964513")));
    	  bookAround = new Book( "Around the world in 80 days", "Jules Verne",new ISBN (Long.parseLong("3326456467846")));
    	  bookPeau = new Book( "La peau de chagrin","Balzac",new ISBN(Long.parseLong("968787565445")));
    	  bookCatch = new Book( "Catch 22", "Joseph Heller",new ISBN(Long.parseLong("465789453149"))); 
    	     booksTest.add(bookCatch);
    		 booksTest.add(bookPeau);
    		 booksTest.add(bookAround);
    		 booksTest.add(bookHarry);
    		 BookRepository.addBooks(booksTest);
    		   
    }

  

    @Test
    public void member_can_borrow_a_book_if_book_is_available() throws HasLateBooksException, notAvailableBookException{
      System.out.println("\n **member_can_borrow_a_book_if_book_is_available**");
    	Member Person = new Student(3, 5 ,"Jack");
      
       library.borrowBook(bookHarry.getIsbn(), Person, LocalDate.now());
    	
    }

    @Test
    public void borrowed_book_is_no_longer_available() throws HasLateBooksException{
        System.out.println("\n **borrowed_book_is_no_longer_available**");

    	Member Person2 = new Student(3, 5 ,"damien");
    	 Member Person1 = new Student(3, 5 ,"Jack");
         try {
        	 library.borrowBook( bookAround.getIsbn(), Person1, LocalDate.now());
             library.borrowBook( bookAround.getIsbn(), Person2, LocalDate.now());
         }
        catch (notAvailableBookException e) {
        	e.getMessage();
        }

    	
    }

    @Test
    public void residents_are_taxed_10cents_for_each_day_they_keep_a_book() throws HasLateBooksException, notAvailableBookException{
        System.out.println("\n **residents_are_taxed_10cents_for_each_day_they_keep_a_book**");

    	LocalDate date = LocalDate.now();
        LocalDate date2 = LocalDate.of(date.getYear(), date.getMonth(),date.getDayOfMonth()-5);  

    	Member Person1 = new Resident( 5 ,"Jack");
     library.borrowBook( bookPeau.getIsbn(), Person1, date2);
     library.returnBook( bookPeau , Person1);
   	 
    	
    }

    @Test
    public void students_pay_10_cents_the_first_30days() throws HasLateBooksException, notAvailableBookException{
        System.out.println("\n **students_pay_10_cents_the_first_30days**");

    	LocalDate date = LocalDate.now();
        LocalDate date2 = LocalDate.of(date.getYear(), date.getMonth(),date.getDayOfMonth()-3);  

    	Member Person1 = new Student(2, 5 ,"Jack");
         library.borrowBook( bookPeau.getIsbn(), Person1, date2);
         library.returnBook( bookPeau,Person1);
    	
    }

    @Test
    public void students_in_1st_year_are_not_taxed_for_the_first_15days() throws HasLateBooksException, notAvailableBookException{
        System.out.println("\n **students_in_1st_year_are_not_taxed_for_the_first_15days**");

        LocalDate date = LocalDate.now();
        LocalDate date2 = LocalDate.of(date.getYear(), date.getMonthValue(),date.getDayOfMonth()-14);  

    	Member Person1 = new Student(1, 5 ,"Jack");
         library.borrowBook( bookPeau.getIsbn(), Person1, date2);
         library.returnBook( bookPeau,Person1);
    	
    }

    @Test
    public void students_pay_15cents_for_each_day_they_keep_a_book_after_the_initial_30days() throws HasLateBooksException, notAvailableBookException{
    	System.out.println("\n **students_pay_15cents_for_each_day_they_keep_a_book_after_the_initial_30days**");

    	Member Person1 = new Student(3, 5 ,"Jack");
         library.borrowBook( bookPeau.getIsbn(), Person1, LocalDate.of(2019, Month.OCTOBER, 20));
         library.returnBook(bookPeau,Person1);   
    	}

    @Test
    public void residents_pay_20cents_for_each_day_they_keep_a_book_after_the_initial_60days() throws HasLateBooksException, notAvailableBookException{
    	System.out.println("\n **residents_pay_20cents_for_each_day_they_keep_a_book_after_the_initial_60days**");

    	Member Person1 = new Resident(5 ,"Jack");
        library.borrowBook(bookPeau.getIsbn(), Person1, LocalDate.of(2019, Month.AUGUST, 9));
        library.returnBook(bookPeau,Person1); 
    	
    }

    @Test
    public void members_cannot_borrow_book_if_they_have_late_books() throws notAvailableBookException{
    	System.out.println("\n **members_cannot_borrow_book_if_they_have_late_book**");

    	Member Person1 = new Student(3, 5 ,"Jack");
		 library.borrowBook(bookCatch.getIsbn(), Person1, LocalDate.of(2019, Month.OCTOBER, 23));

    	try {
    	        library.borrowBook(bookPeau.getIsbn(), Person1, LocalDate.now());
    	}catch(HasLateBooksException e) {
    		e.getMessage();
    	}
       
     
    	
    }
}
