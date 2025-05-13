package model;

import java.time.LocalDate;

public class Loan {
    private int loanId;
    private int userId;
    private int memberId;
    private int copyId;
    private int itemCopyId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(int i, int itemCopyId, int memberId, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate) {}

    public Loan(int loanId, int userId, int copyId, int itemCopyId, int memberId, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate) {
        this.loanId = loanId;
        this.userId = userId;
        this.memberId = memberId;
        this.copyId = copyId;
        this.itemCopyId = itemCopyId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public int getLoanId() { return loanId; }
    public void setLoanId(int loanId) { this.loanId = loanId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getMemberId() {return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public int getCopyId() { return copyId; }
    public void setCopyId(int copyId) { this.copyId = copyId; }

    public int getItemCopyId() {return itemCopyId; }
    public void setItemCopyId(int itemCopyId) { this.itemCopyId = itemCopyId; }

    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
