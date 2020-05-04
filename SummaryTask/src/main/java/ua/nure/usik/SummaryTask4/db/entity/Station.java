package ua.nure.usik.SummaryTask4.db.entity;

public class Station extends Entity {
    private String name;
    private String nameRu;

    public Station(){

    }

    public Station(String name) {
        this.name = name;
    }

    public Station(String name, String nameRu) {
        this.name = name;
        this.nameRu = nameRu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    @Override
    public String toString() {
        return "Station [ name = " + name +
                ", nameRu = " + nameRu +
                ", getId() = " + super.getId() + "]";
    }
}
