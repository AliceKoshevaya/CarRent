package ua.nure.koshova.finalProject.entity;

import ua.nure.koshova.finalProject.Status;

public class Car {

    private Long id;
    private String carName;
    private int price;
    private String stateNumber;
    private Brand brand;
    private ClassCar classCar;
    private Status status;
    private int totalPrice;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarName() {
        return carName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setClassCar(ClassCar classCar) {
        this.classCar = classCar;
    }

    public ClassCar getClassCar() {
        return classCar;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
            return "Car [name=" + carName + " id=" + getId() + " price=" + price + " state number=" + stateNumber +
        " brand=" + brand + " class=" + classCar + " status=" + status + "]" + System.lineSeparator();

    }

}
