package controller;

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
    public List<Car> getAll() {
        return carService.getCars();
    }

    @RequestMapping(value = "getCar/{id}", method = RequestMethod.GET)
    public Car getCarById(@PathVariable int id) {
        return carService.getById(id);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public List<Car> create(@RequestBody Car car) {
        carService.create(car);
        return carService.getCars();
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public List<Car> delete(@PathVariable int id) {
        return carService.delete(id);
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.PUT)
    public List<Car> edit(@RequestBody Car car, @PathVariable int id) {
        return carService.edit(id, car);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public List<Car> search(@RequestParam String keyword, @RequestParam(required = false, name = "sort") String sort) {
        return carService.search(keyword, sort);
    }
}
