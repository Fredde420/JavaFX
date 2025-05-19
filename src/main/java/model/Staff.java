package model;

public class Staff {
    private int staffID;
    private String fName;
    private String email;
    private String phoneNr;
    private boolean isManager;

    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }

    public String getFName() { return fName; }
    public void setFName(String fName) { this.fName = fName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNr() { return phoneNr; }
    public void setPhoneNr(String phoneNr) { this.phoneNr = phoneNr; }

    public boolean isManager() { return isManager; }
    public void setManager(boolean manager) { isManager = manager; }
}
