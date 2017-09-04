package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner extends AbstractBaseEntity {

    private String firstName;

    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH}, orphanRemoval = true, mappedBy = "owner")
    private List<Car> cars = new ArrayList<>();

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Owner() {
    }

    public Owner(long id, String firstName, String lastName) {
        super.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
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

}
