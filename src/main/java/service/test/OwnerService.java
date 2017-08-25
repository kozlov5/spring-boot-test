package service.test;

import data.dto.OwnerDTO;
import entity.Owner;

import java.util.List;

public interface OwnerService {
    List<OwnerDTO> getOwnersDTO();
    List<Owner> getOwners();
    OwnerDTO getById(long id);
    Owner create(Owner owner);
    String delete(long id);
    Owner edit(Owner owner);
    List<OwnerDTO> search(String keyword, String sort);
}
