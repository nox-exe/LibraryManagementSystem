/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem;

/**
 *
 * @author Nox
 */
public class Borrower {
    private int borrowerId;
    private String firstName;
    private String lastName;
    private String contactNumber;

    // Constructor for a NEW borrower
    public Borrower(String firstName, String lastName, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
    }

    // Constructor for an EXISTING borrower
    public Borrower(int borrowerId, String firstName, String lastName, String contactNumber) {
        this.borrowerId = borrowerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
    }

    // Getters and Setters
    public int getBorrowerId() { return borrowerId; }
    public void setBorrowerId(int borrowerId) { this.borrowerId = borrowerId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    // JComboBox formatting
    @Override
    public String toString() {
        return firstName + " " + lastName + " (ID: " + borrowerId + ")";
    }
}
