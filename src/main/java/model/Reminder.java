package model;

import java.time.LocalDateTime;

public class Reminder {
    private int reminderID;
    private LocalDateTime reminderDate;
    private int userID;
    private int loanID;


    public Reminder(int userID, LocalDateTime reminderDate, int loanID, int reminderID) {
        this.reminderID = reminderID;
        this.userID = userID;
        this.loanID = loanID;
        this.reminderDate = reminderDate;
    }

    public int getReminderID() { return reminderID; }
    public int getUserId() { return userID; }
    public int getLoanID() { return loanID; }
    public LocalDateTime getSentAt() { return reminderDate; }

    @Override
    public String toString() {
        return "Reminder{" +
                "reminderId=" + reminderID +
                ", userId=" + userID +
                ", loadID='" + loanID + '\'' +
                ", sentAt=" + reminderDate +
                '}';
    }
}

