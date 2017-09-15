package controller;

import dao.OwnerDAO;
import data.dto.OwnerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.test.OwnerService;

import java.util.List;

@RestController
@RequestMapping(value = "api/")
public class MainController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private OwnerDAO ownerDAO;

    @RequestMapping(value = "owners", method = RequestMethod.GET)
    public List<OwnerDTO> getOwners() {
        List<OwnerDTO> ownersDTO = ownerService.getOwnersDTO();
        return ownersDTO;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public List<OwnerDTO> search(@RequestParam String keyword, @RequestParam(required = false) String sort) {

        return ownerService.search(keyword, sort);
    }
}
