package model;

public class Item {
    private int itemId;
    private String title;
    private String type;
    private String author;
    private String isbn;
    private String classification;
    private String genre;
    private int ageLimit;
    private String country;

    public Item() {}

    public Item(int itemId, String title, String type, String author, String isbn, String classification, String genre, int ageLimit, String country) {
        this.itemId = itemId;
        this.title = title;
        this.type = type;
        this.author = author;
        this.isbn = isbn;
        this.classification = classification;
        this.genre = genre;
        this.ageLimit = ageLimit;
        this.country = country;
    }

    // Getters and setters
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getClassification() { return classification; }
    public void setClassification(String classification) { this.classification = classification; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getAgeLimit() { return ageLimit; }
    public void setAgeLimit(int ageLimit) { this.ageLimit = ageLimit; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}

