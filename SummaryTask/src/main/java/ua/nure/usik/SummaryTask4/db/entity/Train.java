package ua.nure.usik.SummaryTask4.db.entity;

public class Train extends Entity {
    private int typeId;

    public Train(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "User [ typeId = " + typeId +
                ", getId() = " + super.getId()  + "]";
    }
}
