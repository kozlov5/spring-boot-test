package entity;

public class Car {
    private int id;
    private int ownerId;
    private String name;
    private String model;

    public Car() {

    }

    public Car(int id, String name, String model, int ownerId) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
