package service.test.impl;

import data.dto.OwnerDTO;
import entity.Car;
import entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.test.CarService;
import service.test.OwnerService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    private List<Owner> owners = new ArrayList<>();

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    @Autowired
    private CarService carService;

    @Override
    public List<Owner> getOwners() {
        return owners;
    }

    @Override
    public Owner getById(long id) {
        return owners.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Owner> create(Owner owner) {
        owner.setId(owners.size() + 1);
        owners.add(owner);
        return owners;
    }

    @Override
    public List<Owner> delete(long id) {
        if (checkId(id)) {
            owners = owners.stream().filter(f -> f.getId() != id).collect(Collectors.toList());
            List<Car> cars = carService.getCars();
            cars.forEach(car -> {
                if (car.getOwnerId() == id) {
                    carService.delete(car.getId());
                }
            });
        }
        return owners;
    }

    @Override
    public List<Owner> search(String keyword, String sort) {
        String finalKeyword = keyword.toLowerCase();
        List<Owner> searchResult =
                owners.stream().filter(f ->
                        f.getFirstName().toLowerCase().contains(finalKeyword)
                        || f.getLastName().toLowerCase().contains(finalKeyword)
                        || (f.getFirstName() + " " + f.getLastName()).toLowerCase().contains(finalKeyword)
                ).collect(Collectors.toList());
        if (searchResult.size() > 0 || sort != null) {
            if (sort.equals("down")) {
                searchResult.sort((a, b) -> (b.getFirstName() + " " + b.getLastName()).compareTo(a.getFirstName() + " " + a.getLastName()));
            } else if (sort.equals("up")) {
                searchResult.sort(Comparator.comparing(a -> (a.getFirstName() + " " + a.getLastName())));
            }
        }
        return searchResult;
    }

    @Override
    public List<Owner> edit(long id, Owner owner) {
        if (checkId(id)) {
            Owner result = owners.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
            result.setFirstName(owner.getFirstName());
            result.setLastName(owner.getLastName());
        }
        return owners;
    }

    @Override
    public boolean checkId(long id) {
        return owners.stream().filter(f -> f.getId() == id).collect(Collectors.toList()).size() > 0;
    }

    @Override
    public List<OwnerDTO> getDTO(List<Owner> owners) {
        List<OwnerDTO> ownersDTO = new ArrayList<>();
        owners.forEach(owner -> {
            OwnerDTO ownerDTO = new OwnerDTO();
            ownerDTO.setId(owner.getId());
            ownerDTO.setName(owner.getFirstName() + " " + owner.getLastName());
            ownerDTO.setCars(carService.getCarsByOwnerId(owner.getId()));
            ownersDTO.add(ownerDTO);
        });
        return ownersDTO;
    }
}
