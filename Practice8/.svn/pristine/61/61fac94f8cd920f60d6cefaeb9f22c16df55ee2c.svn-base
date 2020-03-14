package ua.nure.usik.practice8.db.entity;

public class User {
    private String login;
    private int id = 0;

    public static User createUser(String login) {
        User user = new User();
        user.login = login;
        return user;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getLogin();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }

        if(this.getClass() != obj.getClass()){
            return false;
        }

        User user = (User)obj;

        return user.getLogin().equals(this.getLogin());
    }
}
