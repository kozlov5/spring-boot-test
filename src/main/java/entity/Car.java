package entity;

import javax.persistence.*;

/**
 * Сущность автомобиль
 */
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private long id;

    /**
     * Название автомобиля
     */
    private String name;

    /**
     * Модель автомобиля
     */
    private String model;

    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Owner owner;


    public Car() {

    }

    public Car(long id, String name, String model) {
        this.id = id;
        this.name = name;
        this.model = model;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


}
