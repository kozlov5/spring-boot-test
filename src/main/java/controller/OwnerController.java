package controller;

import entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.test.CarService;
import service.test.OwnerService;

import java.util.List;

@RestController
@RequestMapping("owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private CarService carService;

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<Owner> getAll() {
        return ownerService.getOwners();
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public List<Owner> create(@RequestBody Owner owner) {
        return ownerService.create(owner);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public List<Owner> delete(@PathVariable int id) {
        return ownerService.delete(id);
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public List<Owner> edit(@RequestBody Owner owner, @PathVariable int id) {
        return ownerService.edit(id, owner);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public List<Owner> search(@RequestParam String keyword, @RequestParam(required = false, name = "sort") String sort) {
        return ownerService.search(keyword, sort);
    }
}
