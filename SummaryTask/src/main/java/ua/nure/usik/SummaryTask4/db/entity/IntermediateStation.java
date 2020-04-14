package ua.nure.usik.SummaryTask4.db.entity;

public class IntermediateStation extends Entity {
    private int routeId;
    private int stationId;
    private int scheduleId;

    public IntermediateStation(int routeId, int stationId, int scheduleId) {
        this.routeId = routeId;
        this.stationId = stationId;
        this.scheduleId = scheduleId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Override
    public String toString() {
        return "IntermediateStation [" +
                "routeId=" + routeId +
                ", stationId=" + stationId +
                ", scheduleId=" + scheduleId +
                ']';
    }
}
