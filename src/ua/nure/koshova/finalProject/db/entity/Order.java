package ua.nure.koshova.finalProject.db.entity;

import java.sql.Timestamp;

public class Order {

    private Long id;
    private boolean driver;
    private Timestamp startRent;
    private Timestamp endRent;
    private User user;
    private Car car;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDriver(boolean driver) {
        this.driver = driver;
    }

    public boolean isDriver() {
        return driver;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public void setStartRent(Timestamp startRent) {
        this.startRent = startRent;
    }

    public Timestamp getStartRent() {
        return startRent;
    }

    public void setEndRent(Timestamp endRent) {
        this.endRent = endRent;
    }

    public Timestamp getEndRent() {
        return endRent;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
