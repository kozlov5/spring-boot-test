package service.test;

import data.dto.OwnerDTO;
import entity.Owner;

import java.util.List;

public interface OwnerService {
    List<Owner> getOwners();
    Owner getById(long id);
    Owner create(Owner owner);
    String delete(long id);
    Owner edit(Owner owner);
    List<Owner> search(String keyword, String sort);
}
