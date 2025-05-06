package model;

import java.time.LocalDate;

public class Reservation {
    private int reservationId;
    private int userId;
    private int itemId;
    private LocalDate reservationDate;

    public Reservation() {}

    public Reservation(int reservationId, int userId, int itemId, LocalDate reservationDate) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.itemId = itemId;
        this.reservationDate = reservationDate;
    }

    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public LocalDate getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDate reservationDate) { this.reservationDate = reservationDate; }
}

