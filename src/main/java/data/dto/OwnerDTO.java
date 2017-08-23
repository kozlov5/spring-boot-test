package data.dto;


import entity.Car;

import java.util.List;

public class OwnerDTO {
    private long id;
    private String name;
    private List<Car> Cars;

    public OwnerDTO() {}

    public OwnerDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public OwnerDTO(long id, String name, List<Car> cars) {
        this.id = id;
        this.name = name;
        Cars = cars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return Cars;
    }

    public void setCars(List<Car> cars) {
        Cars = cars;
    }
}
