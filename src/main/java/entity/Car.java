package entity;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность автомобиль
 */
@Entity
public class Car extends AbstractBaseEntity {

    /**
     * Название автомобиля
     */
    private String name;

    /**
     * Модель автомобиля
     */
    private String model;

    @ManyToOne(cascade = { CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "car_details",
            joinColumns = {
                    @JoinColumn(name = "car_id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "details_id", nullable = false)})
    private List<Details> details;


    public Car() {

    }

    public Car(long id, String name, String model) {
        super.setId(id);
        this.name = name;
        this.model = model;
    }

    public long getId() {
        return super.getId();
    }

    public void setId(long id) {
        super.setId(id);
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }
}
