package controller;

import data.dto.OwnerDTO;
import entity.Car;
import entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.test.CarService;
import service.test.OwnerService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private CarService carService;

    @Autowired
    private OwnerService ownerService;

    @RequestMapping(value = "owners", method = RequestMethod.GET)
    public List<OwnerDTO> getOwners() {
        List<Owner> owners = ownerService.getOwners();
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
