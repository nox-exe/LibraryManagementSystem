/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem;

/**
 *
 * @author Nox
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    // Genre Operation(s)
    public static List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        String sql = "SELECT * FROM genres";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                genres.add(new Genre(rs.getInt("genre_id"), rs.getString("genre_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }
    
    // Book Operation(s) (Add, View, Delete)
    public static void addBook(Book book) {
        String sql = "INSERT INTO books (title, author, genre_id, publication_year, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getGenreId());
            pstmt.setInt(4, book.getPublicationYear());
            pstmt.setString(5, book.getStatus());
            
            pstmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.book_id, b.title, b.author, b.genre_id, g.genre_name, b.publication_year, b.status " +
                     "FROM books b JOIN genres g ON b.genre_id = g.genre_id";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Book book = new Book(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("genre_id"),
                    rs.getString("genre_name"),
                    rs.getInt("publication_year"),
                    rs.getString("status")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static boolean deleteBook(int bookId) {
        String deleteRecordsSql = "DELETE FROM borrow_records WHERE book_id = ?";
        String deleteBookSql = "DELETE FROM books WHERE book_id = ?";
        
        java.sql.Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            
            try (java.sql.PreparedStatement deleteRecordsStmt = conn.prepareStatement(deleteRecordsSql)) {
                deleteRecordsStmt.setInt(1, bookId);
                deleteRecordsStmt.executeUpdate();
            }
            
            try (java.sql.PreparedStatement deleteBookStmt = conn.prepareStatement(deleteBookSql)) {
                deleteBookStmt.setInt(1, bookId);
                deleteBookStmt.executeUpdate();
            }
            
            conn.commit();
            return true;
            
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try { conn.rollback(); } catch (java.sql.SQLException ex) { ex.printStackTrace(); }
            }
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (java.sql.SQLException e) { e.printStackTrace(); }
            }
        }
    }
    
    public static void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, genre_id = ?, publication_year = ?, status = ? WHERE book_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getGenreId());
            pstmt.setInt(4, book.getPublicationYear());
            pstmt.setString(5, book.getStatus());
            pstmt.setInt(6, book.getBookId());
            
            pstmt.executeUpdate();
            System.out.println("Book updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Borrower Operation(s) (Add, View, Delete)
    public static void addBorrower(Borrower borrower) {
        String sql = "INSERT INTO borrowers (first_name, last_name, contact_number) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, borrower.getFirstName());
            pstmt.setString(2, borrower.getLastName());
            pstmt.setString(3, borrower.getContactNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Borrower> getAllBorrowers() {
        List<Borrower> borrowers = new ArrayList<>();
        String sql = "SELECT * FROM borrowers";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Borrower b = new Borrower(
                    rs.getInt("borrower_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("contact_number")
                );
                borrowers.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowers;
    }

    public static void deleteBorrower(int borrowerId) {
        String sql = "DELETE FROM borrowers WHERE borrower_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, borrowerId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateBorrower(Borrower borrower) {
        String sql = "UPDATE borrowers SET first_name = ?, last_name = ?, contact_number = ? WHERE borrower_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, borrower.getFirstName());
            pstmt.setString(2, borrower.getLastName());
            pstmt.setString(3, borrower.getContactNumber());
            pstmt.setInt(4, borrower.getBorrowerId());
            
            pstmt.executeUpdate();
            System.out.println("Borrower updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Circulation Operation(s) (Borrow, Return)
    public static boolean borrowBook(int bookId, int borrowerId, java.sql.Date borrowDate, java.sql.Date dueDate) {
        String insertRecordSql = "INSERT INTO borrow_records (book_id, borrower_id, borrow_date, due_date) VALUES (?, ?, ?, ?)";
        String updateBookSql = "UPDATE books SET status = 'Borrowed' WHERE book_id = ?";
        
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false); 
            
            try (PreparedStatement insertStmt = conn.prepareStatement(insertRecordSql)) {
                insertStmt.setInt(1, bookId);
                insertStmt.setInt(2, borrowerId);
                insertStmt.setDate(3, borrowDate);
                insertStmt.setDate(4, dueDate);
                insertStmt.executeUpdate();
            }
            
            try (PreparedStatement updateStmt = conn.prepareStatement(updateBookSql)) {
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();
            }
            
            conn.commit(); 
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    public static boolean returnBook(int borrowId, int bookId, java.sql.Date returnDate) {
        String updateRecordSql = "UPDATE borrow_records SET return_date = ? WHERE borrow_id = ?";
        String updateBookSql = "UPDATE books SET status = 'Available' WHERE book_id = ?";
        
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false); 
            
            try (PreparedStatement updateRecordStmt = conn.prepareStatement(updateRecordSql)) {
                updateRecordStmt.setDate(1, returnDate);
                updateRecordStmt.setInt(2, borrowId);
                updateRecordStmt.executeUpdate();
            }
            
            try (PreparedStatement updateBookStmt = conn.prepareStatement(updateBookSql)) {
                updateBookStmt.setInt(1, bookId);
                updateBookStmt.executeUpdate();
            }
            
            conn.commit();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
    
    // Borrow Records Operation(s)
    public static java.util.ArrayList<BorrowRecord> getAllBorrowRecords() {
        java.util.ArrayList<BorrowRecord> records = new java.util.ArrayList<>();
        String query = "SELECT br.borrow_id, br.book_id, b.title, CONCAT(p.first_name, ' ', p.last_name) AS patron_name, " +
                       "br.borrow_date, br.due_date, br.return_date " +
                       "FROM borrow_records br " +
                       "JOIN books b ON br.book_id = b.book_id " +
                       "JOIN borrowers p ON br.borrower_id = p.borrower_id";
                       
        try (java.sql.Connection conn = getConnection();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(query);
             java.sql.ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                records.add(new BorrowRecord(
                    rs.getInt("borrow_id"),
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("patron_name"),
                    rs.getDate("borrow_date"),
                    rs.getDate("due_date"),
                    rs.getDate("return_date")
                ));
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}
