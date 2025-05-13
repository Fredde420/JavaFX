package Databas;

import java.math.BigDecimal;

public class Fines {
    private int fineID;
    private Integer loanID;
    private BigDecimal fineAmount;
    private Boolean isPaid;

    public Fines(int fineID, Integer loanID, BigDecimal fineAmount, Boolean isPaid) {
        this.fineID = fineID;
        this.loanID = loanID;
        this.fineAmount = fineAmount;
        this.isPaid = isPaid;
    }

    public int getFineID() { return fineID; }
    public Integer getLoanID() { return loanID; }
    public BigDecimal getFineAmount() { return fineAmount; }
    public Boolean getisPaid() { return isPaid; }

    @Override
    public String toString() {
        return "Fine{" +
                "fineID=" + fineID +
                ", loanId=" + loanID +
                ", amount=" + fineAmount +
                ", paid=" + isPaid +
                '}';
    }
}

