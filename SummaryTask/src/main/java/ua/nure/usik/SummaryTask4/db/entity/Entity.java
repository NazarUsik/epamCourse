package ua.nure.usik.SummaryTask4.db.entity;

public abstract class Entity {
    private int id;

    public Entity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
