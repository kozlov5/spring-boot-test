package dao;

import entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OwnerDAO extends JpaRepository<Owner, Long> {

    /**
     * Поиск по владельцам и автомобилям по ключевому слову.
     * @param keyword - ключевое слово для поиска.
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT o.* " +
            "FROM owner o LEFT JOIN car c ON o.id = c.owner_id WHERE " +
            "LOWER(CONCAT(c.name, ' ', c.model)) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(CONCAT(o.first_name, ' ', o.last_name)) LIKE LOWER(CONCAT('%', :keyword, '%')) GROUP BY o.id")
    List<Owner> findByNameAndCars(@Param("keyword") String keyword);

}
