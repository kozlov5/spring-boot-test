package data.dto;


import entity.Car;

import java.util.List;

public class OwnerDTO {
    private int id;
    private String name;
    private List<Car> Cars;

    public OwnerDTO() {}

    public OwnerDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public OwnerDTO(int id, String name, List<Car> cars) {
        this.id = id;
        this.name = name;
        Cars = cars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
