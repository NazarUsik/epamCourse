package ua.nure.usik.SummaryTask4.db.entity;

public class Ticket extends Entity {
    private int routId;
    private float price;
    private int carriageId;
    private int seatId;

    public Ticket(int routId, float price, int carriageId, int seatId) {
        this.routId = routId;
        this.price = price;
        this.carriageId = carriageId;
        this.seatId = seatId;
    }

    public int getRoutId() {
        return routId;
    }

    public void setRoutId(int routId) {
        this.routId = routId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCarriageId(int carriageId) {
        this.carriageId = carriageId;
    }

    public int getCarriageId() {
        return carriageId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    @Override
    public String toString() {
        return "Ticket [ routId = " + routId +
                ", price = " + price +
                ", carriageId = " + carriageId +
                ", seatId = " + seatId +
                ", getId() = " + super.getId()  + "]";
    }
}
