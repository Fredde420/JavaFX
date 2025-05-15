package model;

import java.time.LocalDateTime;

public class Purchase {
    private int purchaseID;
    private LocalDateTime purchaseDate;
    private int quantity;
    private String supplier;
    private int costPerItem;
    private int totalCost;
    private int itemID;
    private int staffID;

    public Purchase(int purchaseID, LocalDateTime purchaseDate, int quantity, String supplier,
                    int costPerItem, int totalCost, int itemID, int staffID) {
        this.purchaseID = purchaseID;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
        this.supplier = supplier;
        this.costPerItem = costPerItem;
        this.totalCost = totalCost;
        this.itemID = itemID;
        this.staffID = staffID;
    }

    public int getPurchaseID() { return purchaseID; }
    public LocalDateTime getPurchaseDate() { return purchaseDate; }
    public int getQuantity() { return quantity; }
    public String getSupplier() { return supplier; }
    public int getCostPerItem() { return costPerItem; }
    public int getTotalCost() { return totalCost; }
    public int getItemID() { return itemID; }
    public int getStaffID() { return staffID; }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseID=" + purchaseID +
                ", purchaseDate=" + purchaseDate +
                ", quantity=" + quantity +
                ", supplier='" + supplier + '\'' +
                ", costPerItem=" + costPerItem +
                ", totalCost=" + totalCost +
                ", itemID=" + itemID +
                ", staffID=" + staffID +
                '}';
    }
}
