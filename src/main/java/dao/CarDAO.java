package dao;

import entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Vladimir on 23.08.2017.
 */
public interface CarDAO extends JpaRepository<Car, Long> {
}
