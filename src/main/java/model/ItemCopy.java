package model;

public class ItemCopy {
    private int copyId;
    private int itemId;
    private String barcode;
    private String location;
    private boolean isAvailable;

    public ItemCopy() {}

    public ItemCopy(int copyId, int itemId, String barcode, String location, boolean isAvailable) {
        this.copyId = copyId;
        this.itemId = itemId;
        this.barcode = barcode;
        this.location = location;
        this.isAvailable = isAvailable;
    }

    public int getCopyId() { return copyId; }
    public void setCopyId(int copyId) { this.copyId = copyId; }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}

