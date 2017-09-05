package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Vladimir on 04.09.2017.
 */
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class AbstractBaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private Long id;
}
