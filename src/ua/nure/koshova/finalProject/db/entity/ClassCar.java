package ua.nure.koshova.finalProject.db.entity;

public class ClassCar extends Entity {

    private int coefficient;

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getCoefficient() {
        return coefficient;
    }

    @Override
    public String toString() {
        return getName() + " ";
    }
}
