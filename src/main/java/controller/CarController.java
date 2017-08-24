package controller;

import data.dto.CarDTO;
import entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.test.CarService;
import service.test.OwnerService;

import java.util.List;

@RestController
@RequestMapping(value = "car")
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<CarDTO> getAll() {
        return carService.getCars();
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public CarDTO getCarById(@PathVariable long id) {
        return carService.getById(id);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public List<CarDTO> create(@RequestBody Car car) {
        carService.create(car);
        return carService.getCars();
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable long id) {
        return carService.delete(id);
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.PUT)
    public Car edit(@RequestBody Car car, @PathVariable long id) {
        return carService.edit(car);
    }
}
