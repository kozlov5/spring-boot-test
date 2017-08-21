package service.test;

import entity.Owner;

import java.util.List;

public interface OwnerService {
    List<Owner> getOwners();
    List<Owner> create(Owner owner);
    List<Owner> delete(int id);
    List<Owner> search(String keyword, String sort);
    List<Owner> edit(int id, Owner owner);
    boolean checkId(int id);

}
