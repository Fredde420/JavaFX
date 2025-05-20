package model;

public class LoanWithItemTitle {
    private int loanID;
    private String loanDate;
    private String dueDate;
    private String status;
    private String title;

    public LoanWithItemTitle(int loanID, String loanDate, String dueDate, String status, String title) {
        this.loanID = loanID;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.status = status;
        this.title = title;
    }

    public int getLoanID() {
        return loanID;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }
}
