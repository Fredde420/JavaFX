package model;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Review {
    private final IntegerProperty userId;
    private final IntegerProperty itemId;
    private final IntegerProperty rating;
    private final StringProperty text;
    private final ObjectProperty<LocalDate> date;

    public Review(int userId, int itemId, int rating, String text, LocalDate date) {
        this.userId = new SimpleIntegerProperty(userId);
        this.itemId = new SimpleIntegerProperty(itemId);
        this.rating = new SimpleIntegerProperty(rating);
        this.text = new SimpleStringProperty(text);
        this.date = new SimpleObjectProperty<>(date);
    }

    public int getUserId() {
        return userId.get();
    }
    public int getItemId() {
        return itemId.get();
    }
    public int getRating() {
        return rating.get();
    }
    public String getText() {
        return text.get();
    }
    public LocalDate getDate() {
        return date.get();
    }

    public void setItemId(int itemId) {
        this.itemId.set(itemId);
    }
    public IntegerProperty userIdProperty() {
        return userId;
    }
    public IntegerProperty itemIdProperty() {
        return itemId;
    }
    public IntegerProperty ratingProperty() {
        return rating;
    }
    public StringProperty textProperty() {
        return text;
    }
    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }
}
