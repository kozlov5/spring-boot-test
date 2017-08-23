package service.test.impl;

import dao.OwnerDAO;
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

    @Autowired
    private OwnerDAO ownerDAO;

    private List<Owner> owners = new ArrayList<>();

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    @Autowired
    private CarService carService;

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
