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
    public List<Owner> getOwners() {
        return ownerDAO.findAll();
    }

    @Override
    public Owner getById(long id) {
        return ownerDAO.getOne(id);
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
    public List<Owner> search(String keyword, String sort) {
        String finalKeyword = keyword.toLowerCase();
        List<Owner> searchResult = ownerDAO.findByNameAndCars(finalKeyword);
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
    public Owner edit(Owner owner) {
        return ownerDAO.save(owner);
    }
}
