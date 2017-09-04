package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by Vladimir on 04.09.2017.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Details extends AbstractBaseEntity {

	private String text;

}
