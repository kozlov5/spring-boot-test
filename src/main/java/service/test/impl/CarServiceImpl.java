package service.test.impl;

import dao.CarDAO;
import data.dto.CarDTO;
import entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.test.CarService;
import service.test.OwnerService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDAO carDAO;

    @Override
    public List<CarDTO> getCars() {
        List<Car> cars = carDAO.findAll();
        return cars.stream().map(CarDTO::new).collect(Collectors.toList());
    }

    @Override
    public Car create(Car car) {
        return carDAO.save(car);
    }

    @Override
    public String delete(long id) {
        try {
            carDAO.delete(id);
            return "OK";
        } catch (Throwable ex) {
            return ex.toString();
        }
    }

    @Override
    public Car edit(Car car) {
        return carDAO.save(car);
    }

    @Override
    public Car getById(long id) {
        return carDAO.getOne(id);
    }
}
