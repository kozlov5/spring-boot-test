package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Сущность автомобиль
 */
@EqualsAndHashCode(callSuper = true, exclude = {"owner", "details"})
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car extends AbstractBaseEntity implements Serializable{

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
}
