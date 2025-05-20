package model;

public class StaffLoanView {
    private int loanID;
    private String title;
    private String loanDate;
    private String dueDate;
    private String username;
    private long daysRemaining;

    public StaffLoanView(int loanID, String title, String loanDate, String dueDate, String username, long daysRemaining) {
        this.loanID = loanID;
        this.title = title;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.username = username;
        this.daysRemaining = daysRemaining;
    }

    public int getLoanID() { return loanID; }
    public String getTitle() { return title; }
    public String getLoanDate() { return loanDate; }
    public String getDueDate() { return dueDate; }
    public String getUsername() { return username; }
    public long getDaysRemaining() { return daysRemaining; }
}