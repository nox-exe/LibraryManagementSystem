/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem;

/**
 *
 * @author Nox
 */
public class Book {
    private int bookId;
    private String title;
    private String author;
    private int genreId;
    private String genreName;
    private int publicationYear;
    private String status;
    
    // Constructor for a NEW book
    public Book(String title, String author, int genreId, int publicationYear, String status){
        this.title = title;
        this.author = author;
        this.genreId = genreId;
        this.publicationYear = publicationYear;
        this.status = status;
    }
    
    // Constructor for an EXISTING book
    public Book(int bookId, String title, String author, int genreId, String genreName, int publicationYear, String status){
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genreId = genreId;
        this.genreName = genreName;
        this.publicationYear = publicationYear;
        this.status = status;
    }
    
    // Getters and Setters
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getGenreId() { return genreId; }
    public void setGenreId(int genreId) { this.genreId = genreId; }

    public String getGenreName() { return genreName; }
    public void setGenreName(String genreName) { this.genreName = genreName; }

    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    @Override
    public String toString() {
        return title + " by " + author;
    }
}
