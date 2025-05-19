package model;

public class UserCategory {
    private int categoryID;
    private String categoryName;
    private int maxLoans;
    private int loanPeriodBooks;
    private int loanPeriodCourseLit;
    private int loanPeriodDVDs;

    public int getCategoryID() { return categoryID; }
    public void setCategoryID(int categoryID) { this.categoryID = categoryID; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public int getMaxLoans() { return maxLoans; }
    public void setMaxLoans(int maxLoans) { this.maxLoans = maxLoans; }

    public int getLoanPeriodBooks() { return loanPeriodBooks; }
    public void setLoanPeriodBooks(int loanPeriodBooks) { this.loanPeriodBooks = loanPeriodBooks; }

    public int getLoanPeriodCourseLit() { return loanPeriodCourseLit; }
    public void setLoanPeriodCourseLit(int loanPeriodCourseLit) { this.loanPeriodCourseLit = loanPeriodCourseLit; }

    public int getLoanPeriodDVDs() { return loanPeriodDVDs; }
    public void setLoanPeriodDVDs(int loanPeriodDVDs) { this.loanPeriodDVDs = loanPeriodDVDs; }
}
