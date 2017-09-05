package dao;

import entity.Details;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsDAO extends JpaRepository<Details, Long> {
}
