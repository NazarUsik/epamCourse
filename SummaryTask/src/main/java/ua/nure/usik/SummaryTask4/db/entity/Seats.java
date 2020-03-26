package ua.nure.usik.SummaryTask4.db.entity;

public class Seats extends Entity {
    private int carriageId;
    private int number;
    private boolean available;

    public Seats(int carriageId, int number, boolean available) {
        this.carriageId = carriageId;
        this.number = number;
        this.available = available;
    }

    public int getCarriageId() {
        return carriageId;
    }

    public void setCarriageId(int carriageId) {
        this.carriageId = carriageId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Seats [ carriageId = " + carriageId +
                ", number = " + number +
                ", available = " + available +
                ", getId() = " + super.getId()  + "]";
    }
}
