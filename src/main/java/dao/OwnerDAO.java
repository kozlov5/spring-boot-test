package dao;

import entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerDAO extends JpaRepository<Owner, Long> {

}
