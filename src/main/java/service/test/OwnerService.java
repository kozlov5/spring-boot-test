package service.test;

import data.dto.OwnerDTO;
import entity.Owner;

import java.util.List;

public interface OwnerService {
    List<Owner> search(String keyword, String sort);
    List<OwnerDTO> getDTO(List<Owner> owners);
}
