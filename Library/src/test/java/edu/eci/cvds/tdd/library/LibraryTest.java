package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.Library;
import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for Library class using JUnit 5.
 */
public class LibraryTest {

    @Test
    public void addBookSuccessfully() {
        Library library = new Library();
        Book book = new Book("Libro 1", "Juan L1", "123456");

        boolean result = library.addBook(book);

        assertTrue(result, "The book should be added successfully");
        assertEquals(1, library.getBooks().get(book), "The number of copies of the book should be 1");
    }

    @Test
    public void addBookIncreasesQuantityWhenBookAlreadyExists() {
        Library library = new Library();
        Book book = new Book("Libro 2", "Juan L2", "654321");

        library.addBook(book);
        library.addBook(book);

        assertEquals(2, library.getBooks().get(book), "The number of copies of the book should be 2");
    }

    @Test
    public void addMultipleDifferentBooks() {
        Library library = new Library();
        Book book1 = new Book("Libro 3", "Juan L3", "987654");
        Book book2 = new Book("Libro 4", "Juan L4", "789123");

        library.addBook(book1);
        library.addBook(book2);

        assertEquals(1, library.getBooks().get(book1), "The number of copies of the first book should be 1");
        assertEquals(1, library.getBooks().get(book2), "The number of copies of the second book should be 1");
    }

    @Test
    public void addBookFailsIfBookIsNull() {
        Library library = new Library();

        boolean result = library.addBook(null);

        assertFalse(result, "Adding a null book should fail");
    }

    @Test
    public void addBookAndVerifyCorrectAmountAfterLoan() {
        Library library = new Library();
        Book book = new Book("Libro 5", "Juan L5", "333444");
        User user = new User();
        user.setId("5");
        user.setName("Juan");

        library.addBook(book);
        library.addUser(user);

        Loan loan = library.loanABook(user.getId(), book.getIsbn());

        assertNotNull(loan, "Loan should be created successfully");
        assertEquals(0, library.getBooks().get(book), "The number of copies of the book should be 0 after loaning it");
    }

    @Test
    public void loanBookSuccessfully() {
        Library library = new Library();
        Book book = new Book("Libro 1", "Juan L1", "123456");
        User user = new User();
        user.setId("1");
        user.setName("Juan");

        library.addBook(book);
        library.addUser(user);

        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        assertNotNull(loan, "Loan should not be null");
        assertEquals(LoanStatus.ACTIVE, loan.getStatus(), "Loan status should be ACTIVE");
        assertEquals(book, loan.getBook(), "Loan book should match");
        assertEquals(user, loan.getUser(), "Loan user should match");
    }

    @Test
    public void loanFailsIfBookNotAvailable() {
        Library library = new Library();
        Book book = new Book("Libro 2", "Juan L2", "654321");
        User user = new User();
        user.setId("2");
        user.setName("Juan2");

        library.addUser(user);  // Se añade el usuario sin libro

        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        assertNull(loan, "Loan should be null if book is not available");
    }

    @Test
    public void loanFailsIfUserDoesNotExist() {
        Library library = new Library();
        Book book = new Book("Libro 3", "Juan L3", "987654");

        library.addBook(book);  // Añadimos libro sin añadir usuario

        Loan loan = library.loanABook(null, book.getIsbn());
        assertNull(loan, "Loan should be null if user does not exist");
    }

    @Test
    public void loanFailsIfUserAlreadyHasActiveLoanForSameBook() {
        Library library = new Library();
        Book book = new Book("Libro 4", "Juan L4", "111222");
        User user = new User();
        user.setId("3");
        user.setName("Juan");

        library.addBook(book);
        library.addUser(user);

        Loan firstLoan = library.loanABook(user.getId(), book.getIsbn());
        assertNotNull(firstLoan, "First loan should be add");

        Loan secondLoan = library.loanABook(user.getId(), book.getIsbn());
        assertNull(secondLoan, "Second loan should fail if user already has the book");
    }

    @Test
    public void loanDateIsSetCorrectly() {
        Library library = new Library();
        Book book = new Book("Libro 5", "Juan L5", "333444");
        User user = new User();
        user.setId("4");
        user.setName("Juan");

        library.addBook(book);
        library.addUser(user);

        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        assertNotNull(loan, "Loan created");
        assertEquals(LocalDateTime.now().toLocalDate(), loan.getLoanDate().toLocalDate(), "Loan date should be the current date");
    }

    @Test
    public void returnLoanWithNullLoan() {
        Library library = new Library();
        Loan loan = null;
        Loan result = library.returnLoan(loan);
        assertNull(result, "Returning a null loan should return null");
    }

    @Test
    public void returnLoanWhenLoanIsNotActive() {
        Library library = new Library();
        Loan loan = new Loan();
        loan.setStatus(LoanStatus.RETURNED); // Establecer el estado a "RETURNED"

        Loan result = library.returnLoan(loan);
        assertNull(result, "A loan that is not active should not be able to be repaid");
    }

    @Test
    public void returnActiveLoan() {
        Library library = new Library();
        Book book = new Book("Libro1", "G. Marquez", "12345");
        library.addBook(book);

        User user = new User();
        library.addUser(user);

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setStatus(LoanStatus.ACTIVE);
        library.getBooks().put(book, 0);

        Loan result = library.returnLoan(loan);

        assertNotNull(result, "The loan should be able to be repaid");
        assertEquals(LoanStatus.RETURNED, result.getStatus(), "The loan status should be 'RETURNED'");
        assertEquals(1, library.getBooks().get(book), "The number of books should have increased by 1");
    }

    @Test
    public void returnNonExistentLoan() {
        Library library = new Library();
        Loan loan = new Loan();

        Loan result = library.returnLoan(loan);
        assertNull(result, "A non-existent loan should not be able to be repaid");
    }

    @Test
    public void returnLoanAlreadyReturned() {
        Library library = new Library();
        Book book = new Book("Libro2", "S. Diaz", "12345");
        library.addBook(book);

        User user = new User();
        library.addUser(user);

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setStatus(LoanStatus.RETURNED);

        Loan result = library.returnLoan(loan);
        assertNull(result, "You should not be able to repay a loan that has already been repaid.");
    }
}
