package ua.nure.koshova.finalProject.db.entity;

public class Car extends Entity {

    private int price;
    private String stateNumber;
    private Brand brand;
    private ClassCar classCar;
    private Status status;
    private int totalPrice;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ClassCar getClassCar() {
        return classCar;
    }

    public void setClassCar(ClassCar classCar) {
        this.classCar = classCar;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Car [name=" + getName() + " id=" + getId() + " price=" + price + " state number=" + stateNumber +
                " brand=" + brand + " class=" + classCar + " status=" + status + "]" + System.lineSeparator();

    }

}
