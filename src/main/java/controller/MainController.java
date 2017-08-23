package controller;

import dao.OwnerDAO;
import data.dto.OwnerDTO;
import entity.Car;
import entity.Owner;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.test.CarService;
import service.test.OwnerService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private CarService carService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private OwnerDAO ownerDAO;

    @RequestMapping(value = "owners", method = RequestMethod.GET)
    public List<Owner> getOwners() {
        List<Owner> owners = ownerDAO.findAll();

        return owners;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public List<OwnerDTO> search(@RequestParam String keyword, @RequestParam(required = false) String sort) {
        LinkedHashSet<Owner> finalOwners = new LinkedHashSet<>(ownerService.search(keyword, sort));

        List<Car> cars = carService.search(keyword, sort);
//        cars.forEach(car -> finalOwners.add(ownerService.getById(car.getOwnerId())));

        return ownerService.getDTO(new ArrayList<>(finalOwners));
    }
}
