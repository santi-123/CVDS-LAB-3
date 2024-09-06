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
    public LibraryTest( String testName ){
        super( testName );
    }

    //Metodo 1

    public void addBookSuccessfully() {
        Library library = new Library();
        Book book = new Book("Libro 1", "Juan L1", "123456");

        boolean result = library.addBook(book);

        assertTrue("The book should be added successfully", result);
        assertEquals("The number of copies of the book should be 1", (Integer) 1, library.getBooks().get(book));
    }

    public void addBookIncreasesQuantityWhenBookAlreadyExists() {
        Library library = new Library();
        Book book = new Book("Libro 2", "Juan L2", "654321");

        // Añadir el mismo libro dos veces
        library.addBook(book);
        library.addBook(book);

        assertEquals("The number of copies of the book should be 2", (Integer) 2, library.getBooks().get(book));
    }

    public void addMultipleDifferentBooks() {
        Library library = new Library();
        Book book1 = new Book("Libro 3", "Juan L3", "987654");
        Book book2 = new Book("Libro 4", "Juan L4", "789123");

        library.addBook(book1);
        library.addBook(book2);

        assertEquals("The number of copies of the first book should be 1", (Integer) 1, library.getBooks().get(book1));
        assertEquals("The number of copies of the second book should be 1", (Integer) 1, library.getBooks().get(book2));
    }

    public void addBookFailsIfBookIsNull() {
        Library library = new Library();

        boolean result = library.addBook(null);

        assertFalse("Adding a null book should fail", result);
    }

    public void addBookAndVerifyCorrectAmountAfterLoan() {
        Library library = new Library();
        Book book = new Book("Libro 5", "Juan L5", "333444");
        User user = new User();
        user.setId("5");
        user.setName("Juan");

        library.addBook(book);
        library.addUser(user);

        Loan loan = library.loanABook(user.getId(), book.getIsbn());

        assertNotNull("Loan should be created successfully", loan);
        assertEquals("The number of copies of the book should be 0 after loaning it", (Integer) 0, library.getBooks().get(book));
    }


    // Metodo 2

    public void loanBookSuccessfully() {
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


    public void loanFailsIfBookNotAvailable() {
        Library library = new Library();
        Book book = new Book("Libro 2", "Juan L2", "654321");
        User user = new User();
        user.setId("2");
        user.setName("Juan2");

        library.addUser(user);  // Se añade el usuario sin libro

        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        assertNull("Loan should be null if book is not available", loan);
    }


    public void loanFailsIfUserDoesNotExist() {
        Library library = new Library();
        Book book = new Book("Libro 3", "Juan L3", "987654");

        library.addBook(book);  // Añadimos libro sin añadir usuario

        Loan loan = library.loanABook("An invalid id", book.getIsbn());
        assertNull("Loan should be null if user does not exist", loan);
    }


    public void loanFailsIfUserAlreadyHasActiveLoanForSameBook() {
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


    public void loanDateIsSetCorrectly() {
        Library library = new Library();
        Book book = new Book("Libro 5", "Juan L5", "333444");
        User user = new User();
        user.setId("4");
        user.setName("Juan");

        library.addBook(book);
        library.addUser(user);

        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        assertNotNull("Loany created", loan);
        System.out.println(loan.getLoanDate().toLocalDate());
        assertEquals("Loan date should be the current date", LocalDateTime.now().toLocalDate(), loan.getLoanDate().toLocalDate());
    }


    //Metodo 3

    @Test
    public void testReturnLoanWithNullLoan() {
        Library library = new Library();
        Loan loan = null;
        Loan result = library.returnLoan(loan);
        assertNull(result, "Returning a null loan should return null");
    }

    @Test
    public void testReturnLoanWhenLoanIsNotActive() {
        Library library = new Library();
        Loan loan = new Loan();
        loan.setStatus(LoanStatus.RETURNED); // Establecer el estado a "RETURNED"

        Loan result = library.returnLoan(loan);
        assertNull(result, "A loan that is not active should not be able to be repaid");
    }

    @Test
    public void testReturnActiveLoan() {
        Library library = new Library();

        // Crear el libro y agregarlo a la biblioteca
        Book book = new Book("Libro1", "G. Marquez", "12345",);
        library.addBook(book);

        // Crear un usuario y agregarlo a la biblioteca
        User user = new User();
        library.addUser(user);

        // Crear un préstamo activo
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setStatus(LoanStatus.ACTIVE);
        library.getBooks().put(book, 0); // No quedan copias disponibles del libro

        // Devolver el préstamo
        Loan result = library.returnLoan(loan);

        // Verificar que el préstamo está ahora en estado "RETURNED"
        assertNotNull(result, "The loan should be able to be repaid");
        assertEquals(LoanStatus.RETURNED, result.getStatus(), "The loan status should be 'RETURNED'");

        // Verificar que la cantidad de libros ha aumentado
        assertEquals(1, library.getBooks().get(book), "The number of books should have increased by 1");
    }

}
