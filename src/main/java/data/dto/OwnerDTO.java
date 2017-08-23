package data.dto;


import entity.Car;

import java.util.List;

public class OwnerDTO {
    private long id;
    private String name;
    private List<CarDTO> cars;

    public OwnerDTO() {}

    public OwnerDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public OwnerDTO(long id, String name, List<CarDTO> cars) {
        this.id = id;
        this.name = name;
        cars = cars;
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

    public List<CarDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarDTO> cars) {
        cars = cars;
    }
}
