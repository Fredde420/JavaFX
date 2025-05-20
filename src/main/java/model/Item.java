package model;

public class Item {
    private int itemId;
    private String title;
    private String category;
    private String authorOrArtist;
    private String barcode;
    private String physicalLocation;
    private String classification;

    public Item() {}

    public Item(int itemId, String title, String category, String authorOrArtist,
                String barcode, String physicalLocation, String classification) {
        this.itemId = itemId;
        this.title = title;
        this.category = category;
        this.authorOrArtist = authorOrArtist;
        this.barcode = barcode;
        this.physicalLocation = physicalLocation;
        this.classification = classification;
    }

    public int getItemId() { return itemId; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getAuthorOrArtist() { return authorOrArtist; }
    public String getBarcode() { return barcode; }
    public String getPhysicalLocation() { return physicalLocation; }
    public String getClassification() { return classification; }
}


