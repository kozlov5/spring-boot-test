package data.dto;

import entity.Car;

/**
 * Created by Alexander on 23.08.2017.
 */
public class CarDTO {
    private long id;
    private long ownerId;
    private String name;
    private String model;

    public CarDTO() {

    }

    public CarDTO(Car car) {
        this.id = car.getId();
        this.name = car.getName();
        this.model = car.getModel();
        this.ownerId = car.getOwner().getId();
    }

    public CarDTO(long id, long ownerId, String name, String model) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.model = model;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
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
