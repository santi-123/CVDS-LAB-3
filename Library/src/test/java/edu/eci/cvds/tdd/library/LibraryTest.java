package edu.eci.cvds.tdd;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.eci.cvds.tdd.library.Library;
import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
import junit.framework.TestCase;
import java.time.LocalDateTime;


/**
 * Unit test for simple App.
 */
public class LibraryTest
        extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public LibraryTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void method2Test1LoanBookSuccessfully() {
        Library library = new Library();
        Book book = new Book("Libro 1", "Juan L1", "123456");
        User user = new User();
        user.setId("1");
        user.setName("Juan");

        library.addBook(book);
        library.addUser(user);

        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        assertNotNull("Loan should not be null", loan);
        assertEquals("Loan status should be ACTIVE", LoanStatus.ACTIVE, loan.getStatus());
        assertEquals("Loan book should match", book, loan.getBook());
        assertEquals("Loan user should match", user, loan.getUser());
    }


    public void method2Test2LoanFailsIfBookNotAvailable() {
        Library library = new Library();
        Book book = new Book("Libro 2", "Juan L2", "654321");
        User user = new User();
        user.setId("2");
        user.setName("Juan2");

        library.addUser(user);  // Se añade el usuario sin libro

        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        assertNull("Loan should be null if book is not available", loan);
    }


    public void method2Test3LoanFailsIfUserDoesNotExist() {
        Library library = new Library();
        Book book = new Book("Libro 3", "Juan L3", "987654");

        library.addBook(book);  // Añadimos libro sin añadir usuario

        Loan loan = library.loanABook("An invalid id", book.getIsbn());
        assertNull("Loan should be null if user does not exist", loan);
    }


    public void method2Test4LoanFailsIfUserAlreadyHasActiveLoanForSameBook() {
        Library library = new Library();
        Book book = new Book("Libro 4", "Juan L4", "111222");
        User user = new User();
        user.setId("3");
        user.setName("Juan");

        library.addBook(book);
        library.addUser(user);

        // Primer prestamo 
        Loan firstLoan = library.loanABook(user.getId(), book.getIsbn());
        assertNotNull("First loan should be add", firstLoan);

        // Intentamos un segundo prestamo del mismo libro por el mismo usuario
        Loan secondLoan = library.loanABook(user.getId(), book.getIsbn());
        assertNull("Second loan should fail if user already has the book", secondLoan);
    }


}
