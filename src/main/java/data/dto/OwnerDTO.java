package data.dto;


import entity.Car;
import entity.Owner;

import java.util.List;

public class OwnerDTO {
    private long id;

    /**
     * Полное имя. Складывается из имени и фамилии.
     */
    private String name;
    private String firstName;
    private String lastName;
    private List<CarDTO> cars;

    public OwnerDTO() {}

    /**
     * Клас для маппинга Owner в OwnerDTO без автомобилей.
     * @param owner - сущность владельца.
     */
    public OwnerDTO(Owner owner) {
        this.id = owner.getId();
        this.firstName = owner.getFirstName();
        this.lastName = owner.getLastName();
        this.name = owner.getFirstName() + ' ' + owner.getLastName();
    }

    /**
     * Класс для маппинга Owner в OwnerDTO вместе с автомобилями.
     * @param owner - сущность владельца.
     * @param cars - DTO автомобилей владельца.
     */
    public OwnerDTO(Owner owner, List<CarDTO> cars) {
        this.id = owner.getId();
        this.cars = cars;
        this.name = owner.getFirstName() + ' ' + owner.getLastName();
        this.firstName = owner.getFirstName();
        this.lastName = owner.getLastName();
    }

    public OwnerDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public OwnerDTO(long id, String name, List<CarDTO> cars) {
        this.id = id;
        this.name = name;
        cars = cars;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
