package controller;

import data.dto.CarDTO;
import entity.Car;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.test.CarService;

import java.util.List;

@RestController
@Api(value = "cars", description = "Автомобили")
@RequestMapping(value = "api/car")
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * Получить все автомобили
     * @return
     */
    @ApiOperation(value = "Get cars", notes = "Получить все автомобили")
    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<CarDTO> getAll() {
        return carService.getCarsDTO();
    }

    @ApiOperation(value = "Get car by id", notes = "Получить автомобиль по Id")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public CarDTO getCarById(@PathVariable long id) {
        return carService.getById(id);
    }

    @ApiOperation(value = "Create car", notes = "Создать автомобиль")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public List<CarDTO> create(@RequestBody Car car) {
        carService.create(car);
        return carService.getCarsDTO();
    }

    @ApiOperation(value = "Delete car", notes = "Удалить автомобиль")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable long id) {
        return carService.delete(id);
    }

    @ApiOperation(value = "Edit car", notes = "Изменить автомобиль")
    @RequestMapping(value = "edit/{id}", method = RequestMethod.PUT)
    public Car edit(@RequestBody Car car, @PathVariable long id) {
        return carService.edit(car);
    }
}
