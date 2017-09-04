package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true, exclude = {"cars"})
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends AbstractBaseEntity implements Serializable {

    private String firstName;

    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH}, orphanRemoval = true, mappedBy = "owner")
    private List<Car> cars = new ArrayList<>();

}
