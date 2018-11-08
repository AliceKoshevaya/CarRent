package ua.nure.koshova.finalProject.db.entity;

public class Role extends Entity{

    private Long id;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId(){
        return id;
    }

    @Override
    public String toString() {
        return "Role [name=" + name + "]";
    }

}
