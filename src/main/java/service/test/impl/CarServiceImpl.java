package service.test.impl;

import dao.CarDAO;
import data.dto.CarDTO;
import entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Car create(Car car) {
        delete(car.getId());
        return carDAO.save(car);
    }

    @Override
    @Transactional
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
    public CarDTO getById(long id) {
        Car car = carDAO.getOne(id);
        return new CarDTO(car);
    }
}
