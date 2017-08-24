package service.test.impl;

import dao.OwnerDAO;
import data.dto.CarDTO;
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

    @Override
    public List<OwnerDTO> getOwners() {
        List<Owner> owners = ownerDAO.findAll();
        List<OwnerDTO> ownersDTO = owners.stream().map(owner -> {
          List<CarDTO> carsDTO = owner.getCars().stream().map(CarDTO::new).collect(Collectors.toList());
            return new OwnerDTO(owner, carsDTO);
        }).collect(Collectors.toList());
        return ownersDTO;
    }

    @Override
    public OwnerDTO getById(long id) {
        Owner owner = ownerDAO.getOne(id);
        return new OwnerDTO(owner);
    }

    @Override
    public Owner create(Owner owner) {
        return ownerDAO.save(owner);
    }

    @Override
    public String delete(long id) {
        try {
            ownerDAO.delete(id);
            return "OK";
        } catch (Throwable ex) {
            return ex.toString();
        }
    }

    @Override
    public List<OwnerDTO> search(String keyword, String sort) {
        String finalKeyword = keyword.toLowerCase();
        List<Owner> searchResult = ownerDAO.findByNameAndCars(finalKeyword);
        if (searchResult.size() > 0 || sort != null) {
            if (sort.equals("down")) {
                searchResult.sort((a, b) -> (b.getFirstName() + " " + b.getLastName()).compareTo(a.getFirstName() + " " + a.getLastName()));
            } else if (sort.equals("up")) {
                searchResult.sort(Comparator.comparing(a -> (a.getFirstName() + " " + a.getLastName())));
            }
        }
        List<OwnerDTO> ownersDTO = searchResult.stream().map(owner -> {
            List<CarDTO> carsDTO = owner.getCars().stream().map(CarDTO::new).collect(Collectors.toList());
            return new OwnerDTO(owner, carsDTO);
        }).collect(Collectors.toList());
        return ownersDTO;
    }

    @Override
    public Owner edit(Owner owner) {
        return ownerDAO.save(owner);
    }
}
