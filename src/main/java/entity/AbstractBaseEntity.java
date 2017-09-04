package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Vladimir on 04.09.2017.
 */
@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
