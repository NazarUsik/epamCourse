package ua.nure.usik.SummaryTask4.db.entity;

public class User extends Entity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int roleId;

    public User(){

    }

    public User(String firstName, String lastName, String email, String password, int roleId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User [ firstName = " + firstName +
                ", lastName = " + lastName +
                ", email = " + email +
                ", password = " + password +
                ", roleId = " + roleId +
                ", getId() = " + super.getId() + "]";
    }
}
