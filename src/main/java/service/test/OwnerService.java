package service.test;

import data.dto.OwnerDTO;
import entity.Owner;

import java.util.List;

public interface OwnerService {
    List<Owner> getOwners();
    Owner getById(long id);
    List<Owner> create(Owner owner);
    List<Owner> delete(long id);
    List<Owner> search(String keyword, String sort);
    List<Owner> edit(long id, Owner owner);
    boolean checkId(long id);
    List<OwnerDTO> getDTO(List<Owner> owners);

}
