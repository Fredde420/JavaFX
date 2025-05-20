package model;

import java.time.LocalDate;

public class Loan {
    private int loanId;
    private int itemCopyId;  // motsvarar copyID i databasen
    private int memberId;    // motsvarar userID i databasen
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate; // motsvarar returnedDate i databasen
    private int status; // t.ex. 0 = aktiv, 1 = återlämnad

    public Loan(int i, int copyId, int memberId, LocalDate today, LocalDate due, Object o) {}

    public Loan(int loanId, int itemCopyId, int memberId, LocalDate loanDate,
                LocalDate dueDate, LocalDate returnDate, int status) {
        this.loanId = loanId;
        this.itemCopyId = itemCopyId;
        this.memberId = memberId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    // Getters and setters
    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getItemCopyId() {
        return itemCopyId;
    }

    public void setItemCopyId(int itemCopyId) {
        this.itemCopyId = itemCopyId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

