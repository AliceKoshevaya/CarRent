package ua.nure.koshova.finalProject.entity;

public class User {
    private Long id;
    private String login;
    private String password;
    private String name;
    private String lastName;
    private Role role;

    private String seria;
    private String passDate;
    private String thirdName;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setSeria(String seria) {
        this.seria = seria;
    }

    public String getSeria() {
        return seria;
    }

    public void setPassDate(String passDate) {
        this.passDate = passDate;
    }

    public String getPassDate() {
        return passDate;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getThirdName() {
        return thirdName;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", login" + login + ", password=" + password + ", last name=" + lastName +
                ", name=" + name + ", third name=" + thirdName + ", passport seria=" + seria + ", passport from=" + passDate+
                "]" + System.lineSeparator();

    }
}
