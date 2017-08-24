package controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dao.OwnerDAO;
import data.dto.OwnerDTO;
import entity.Owner;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.test.OwnerService;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private OwnerDAO ownerDAO;

    @RequestMapping(value = "owners", method = RequestMethod.GET)
    public List<OwnerDTO> getOwners() {
        List<OwnerDTO> ownersDTO = ownerService.getOwners();
        return ownersDTO;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public List<OwnerDTO> search(@RequestParam String keyword, @RequestParam(required = false) String sort) {

        return ownerService.search(keyword, sort);
    }
}
