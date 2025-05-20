package model;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Review {
    private final IntegerProperty userId;
    private final IntegerProperty rating;
    private final StringProperty text;
    private final ObjectProperty<LocalDate> date;

    public Review(int userId, int rating, String comment, LocalDate reviewDate) {
        this.userId = new SimpleIntegerProperty(userId);
        this.rating = new SimpleIntegerProperty(rating);
        this.text = new SimpleStringProperty(comment);
        this.date = new SimpleObjectProperty<>(reviewDate);
    }

    public int getUserId() { return userId.get(); }
    public int getRating() { return rating.get(); }
    public String getText() { return text.get(); }
    public LocalDate getDate() { return date.get(); }

    public IntegerProperty userIdProperty() { return userId; }
    public IntegerProperty ratingProperty() { return rating; }
    public StringProperty textProperty() { return text; }
    public ObjectProperty<LocalDate> dateProperty() { return date; }
}
