package dao;

import entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface CarDAO extends JpaRepository<Car, Long> {

	/**
	 * Поиск автомобилей по ID владельца
	 * @param id - ID владельца в БД
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT * FROM car c WHERE c.owner_id = :ownerId")
	List<Car> findByOwnerId(@Param("ownerId") long id);
}
