package dao;

import entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OwnerDAO extends JpaRepository<Owner, Long> {

    /**
     * Поиск по владельцам и автомобилям по ключевому слову.
     * @param keyword - ключевое слово для поиска.
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM owner o LEFT JOIN car c ON o.id = c.owner_id WHERE " +
            "CONCAT(c.name, ' ', c.model) LIKE CONCAT('%', :keyword, '%') OR " +
            "CONCAT(o.first_name, ' ', o.last_name) LIKE CONCAT('%', :keyword, '%')")
    List<Owner> findByNameAndCars(@Param("keyword") String keyword);

}
