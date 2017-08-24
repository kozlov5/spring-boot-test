package controller;

import dao.OwnerDAO;
import data.dto.OwnerDTO;
import entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.test.CarService;
import service.test.OwnerService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<OwnerDTO> getAll() {
        return ownerService.getOwners();
    }

    @RequestMapping(value="get/{id}", method = RequestMethod.GET)
    public OwnerDTO getById(@PathVariable long id) {
        return ownerService.getById(id);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Owner create(@RequestBody Owner owner) {
        return ownerService.create(owner);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable long id) {
        return ownerService.delete(id);
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public Owner edit(@RequestBody Owner owner, @PathVariable long id) {
        return ownerService.edit(owner);
    }
}
