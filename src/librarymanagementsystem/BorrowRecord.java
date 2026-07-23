/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem;

/**
 *
 * @author Nox
 */

import java.sql.Date;

public class BorrowRecord {
    private int borrowId;
    private int bookId;
    private String bookTitle;
    private String patronName;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;

    public BorrowRecord(int borrowId, int bookId, String bookTitle, String patronName, Date borrowDate, Date dueDate, Date returnDate) {
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.patronName = patronName;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    // Getters
    public int getBorrowId() { return borrowId; }
    public int getBookId() { return bookId; }
    public String getBookTitle() { return bookTitle; }
    public String getPatronName() { return patronName; }
    public Date getBorrowDate() { return borrowDate; }
    public Date getDueDate() { return dueDate; }
    public Date getReturnDate() { return returnDate; }
}
