package model;

import java.time.LocalDate;

public class Reservation {
    private int reservationId;
    private int userId;
    private int memberId;
    private int itemId;
    private LocalDate reservationDate;

    public Reservation(int i, int itemId, int memberId, LocalDate date) {}

    public Reservation(int reservationId, int userId, int memberId, int itemId, LocalDate reservationDate) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.memberId = memberId;
        this.itemId = itemId;
        this.reservationDate = reservationDate;
    }

    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getMemberId() {return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public LocalDate getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDate reservationDate) { this.reservationDate = reservationDate; }
}

