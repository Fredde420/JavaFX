package model;

public class User {
    private int userID;
    private String fName;
    private String lName;
    private String email;
    private String phoneNr;
    private int categoryID;
    private int adressNr;

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public String getFName() { return fName; }
    public void setFName(String fName) { this.fName = fName; }

    public String getLName() {return lName;}
    public void setLName(String lName) {this.lName = lName;}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNr() { return phoneNr; }
    public void setPhoneNr(String phoneNr) { this.phoneNr = phoneNr; }

    public int getCategoryID() { return categoryID; }
    public void setCategoryID(int categoryID) { this.categoryID = categoryID; }

    public int getAdressNr() { return adressNr; }
    public void setAdressNr(int adressNr) { this.adressNr = adressNr; }
}
