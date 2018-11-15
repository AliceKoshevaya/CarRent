package ua.nure.koshova.SummaryTask4.db.entity;

public class User extends Entity {

    private String login;
    private String password;
    private String lastName;
    private Role role;
    private boolean block;

    private String seria;
    private String passDate;
    private String thirdName;

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

    public void setBlock(boolean block) {
        this.block = block;
    }

    public boolean isBlock() {
        return block;
    }

    @Override
    public String toString() {
        return "User [id=" + getId() + ", login" + login + ", password=" + password + ", last name=" + lastName +
                ", name=" + getName() + ", third name=" + thirdName + ", passport seria=" + seria + ", passport from=" + passDate+
                "]" + System.lineSeparator();

    }
}
