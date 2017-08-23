package dao;

import entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Vladimir on 23.08.2017.
 */
public interface CarDAO extends JpaRepository<Car, Long> {

	//SELECT * FROM car WHERE first_name = :firstName;
	@Query(nativeQuery = true, value = "SELECT * FROM car c WHERE c.name = :carName")
	List<Car> findByNameAndModel(@Param("carName") String name);
}
