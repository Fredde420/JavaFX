package model;

import java.time.LocalDateTime;

public class Purchase {
    private final LocalDateTime purchaseDate;
    private final int quantity;
    private final String supplier;
    private final int costPerItem;
    private final int totalCost;
    private final int itemID;
    private final int staffID;

    public Purchase(LocalDateTime purchaseDate, int quantity, String supplier,
                    int costPerItem, int totalCost, int itemID, int staffID) {
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
        this.supplier = supplier;
        this.costPerItem = costPerItem;
        this.totalCost = totalCost;
        this.itemID = itemID;
        this.staffID = staffID;
    }


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
