package controller;

import dao.OwnerDAO;
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

    @Autowired
    private OwnerDAO ownerDAO;

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<Owner> getAll() {
        return ownerDAO.findAll();
    }

    @RequestMapping(value="getOwner/{id}", method = RequestMethod.GET)
    public Owner getById(@PathVariable long id) {
        return ownerDAO.getOne(id);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Owner create(@RequestBody Owner owner) {
        return ownerDAO.save(owner);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable long id) {
        try {
            ownerDAO.delete(id);
            return "OK";
        } catch (Throwable ex) {
            return ex.toString();
        }
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public Owner edit(@RequestBody Owner owner, @PathVariable long id) {
        return ownerDAO.save(owner);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public List<Owner> search(@RequestParam String keyword, @RequestParam(required = false, name = "sort") String sort) {
        return ownerService.search(keyword, sort);
    }
}
