package ua.nure.usik.SummaryTask4.db.entity;

public class Route extends Entity {
    private int trainId;
    private int departureId;
    private int arrivalId;
    private int scheduleId;

    public Route() {

    }

    public Route(int trainId, int departureId, int arrivalId, int scheduleId) {
        this.trainId = trainId;
        this.departureId = departureId;
        this.arrivalId = arrivalId;
        this.scheduleId = scheduleId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getDepartureId() {
        return departureId;
    }

    public void setDepartureId(int departureId) {
        this.departureId = departureId;
    }

    public int getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(int arrivalId) {
        this.arrivalId = arrivalId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }


    @Override
    public String toString() {
        return "Route = [ trainId = " + trainId +
                ", departureId = " + departureId +
                ", arrivalId = " + arrivalId +
                ", scheduleId = " + scheduleId +
                ", getId() = " + super.getId() + "]";
    }
}
