package model;

import java.time.LocalDateTime;

public class Reservation {
    private int reservationId;
    private int userId;
    private int copyId;
    private int status;
    private LocalDateTime reservationDate;

    public Reservation(int reservationId, int userId, int copyId, int status, LocalDateTime reservationDate) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.copyId = copyId;
        this.status = status;
        this.reservationDate = reservationDate;
    }

    public Reservation() {
    }

    // Getters
    public int getReservationId() { return reservationId; }
    public int getUserId() { return userId; }
    public int getCopyId() { return copyId; }
    public int getStatus() { return status; }
    public LocalDateTime getReservationDate() { return reservationDate; }

    // Setters
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setCopyId(int copyId) { this.copyId = copyId; }
    public void setStatus(int status) { this.status = status; }
    public void setReservationDate(LocalDateTime reservationDate) { this.reservationDate = reservationDate; }
}


