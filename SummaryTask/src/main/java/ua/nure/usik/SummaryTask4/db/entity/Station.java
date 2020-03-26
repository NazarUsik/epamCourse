package ua.nure.usik.SummaryTask4.db.entity;

public class Station extends Entity {
    private String name;

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Station [ name = " + name +
                ", getId() = " + super.getId()  + "]";
    }
}
