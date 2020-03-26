package ua.nure.usik.SummaryTask4.db.entity;

public class Schedule extends Entity {
    private String departureTime;
    private String arrivalTime;
    private int travelTime;

    public Schedule(String departureTime, String arrivalTime, int travelTime) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.travelTime = travelTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

    @Override
    public String toString() {
        return "Schedule [ departureTime = " + departureTime +
                ", arrivalTime = " + arrivalTime +
                ", travelTime = " + travelTime +
                ", getId() = " + super.getId()  + "]";
    }
}
