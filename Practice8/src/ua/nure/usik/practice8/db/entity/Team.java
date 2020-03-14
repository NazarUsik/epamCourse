package ua.nure.usik.practice8.db.entity;

public class Team {
    private String name;
    private int id;

    public static Team createTeam(String name) {
        Team team = new Team();
        team.name = name;
        return team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }

        if(this.getClass() != obj.getClass()){
            return false;
        }

        Team team = (Team)obj;

        return team.getName().equals(this.getName());
    }
}
