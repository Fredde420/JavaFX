package database;

import model.Reservation;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    public void insertReservation(Reservation reservation) {
        String query = "INSERT INTO reservation (reservationDate, status, userID, copyID) VALUES (?, ?, ?, ?)";

        try (Connection connection = database.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.println("insertReservation called with: " + reservation);

            // Om reservationDate är null, använd nuvarande tid
            LocalDateTime reservationDate = reservation.getReservationDate() != null
                    ? reservation.getReservationDate()
                    : LocalDateTime.now();

            stmt.setTimestamp(1, Timestamp.valueOf(reservationDate));
            stmt.setInt(2, reservation.getStatus());
            stmt.setInt(3, reservation.getUserId());
            stmt.setInt(4, reservation.getCopyId());

            stmt.executeUpdate();
            System.out.println("Reservation inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation";

        try (Connection conn = database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("reservationID"),
                        rs.getInt("userID"),
                        rs.getInt("copyID"),
                        rs.getInt("status"),
                        rs.getTimestamp("reservationDate").toLocalDateTime()
                );
                reservations.add(reservation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }
}

