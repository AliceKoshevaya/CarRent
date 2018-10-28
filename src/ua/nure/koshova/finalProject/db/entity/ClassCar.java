package ua.nure.koshova.finalProject.db.entity;

public class ClassCar {

    private Long id;
    private String name;
    private int coefficient;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getCoefficient() {
        return coefficient;
    }

    @Override
    public String toString() {
        return name + " ";
    }
}
