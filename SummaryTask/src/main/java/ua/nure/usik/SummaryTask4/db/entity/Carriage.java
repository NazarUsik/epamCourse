package ua.nure.usik.SummaryTask4.db.entity;

public class Carriage extends Entity {
    private int typeId;
    private int countSeats;
    private int countAvailableSeats;
    private boolean haveRestaurant;

    public Carriage(int typeId, int countSeats, int countAvailableSeats, boolean haveRestaurant) {
        this.typeId = typeId;
        this.countSeats = countSeats;
        this.countAvailableSeats = countAvailableSeats;
        this.haveRestaurant = haveRestaurant;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getCountSeats() {
        return countSeats;
    }

    public void setCountSeats(int countSeats) {
        this.countSeats = countSeats;
    }

    public int getCountAvailableSeats() {
        return countAvailableSeats;
    }

    public void setCountAvailableSeats(int countAvailableSeats) {
        this.countAvailableSeats = countAvailableSeats;
    }

    public boolean isHaveRestaurant() {
        return haveRestaurant;
    }

    public void setHaveRestaurant(boolean haveRestaurant) {
        this.haveRestaurant = haveRestaurant;
    }

    @Override
    public String toString() {
        return "Carriage [ typeId = " + typeId +
                ", countSeats = " + countSeats +
                ", countAvailableSeats = " + countAvailableSeats +
                ", haveRestaurant = " + haveRestaurant +
                ", getId() = " + super.getId()  + "]";


    }
}
