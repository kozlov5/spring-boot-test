package service.test.impl;

import dao.CarDAO;
import data.dto.CarDTO;
import entity.Car;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.test.CarService;
import service.test.OwnerService;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDAO carDAO;

    @Override
    public List<CarDTO> getCarsDTO() {
        return getCars().stream().map(CarDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<Car> getCars() {
        return carDAO.findAll();
    }

    @Override
    @Transactional
    public Car create(Car car) {
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
    @Transactional
    public Car edit(Car car) {
//        Hibernate.initialize(car.getOwner());
        return carDAO.save(car);
    }

    @Override
    public CarDTO getById(long id) {
        Car car = carDAO.getOne(id);
        return new CarDTO(car);
    }

    @Override
    public List<Car> getByOwnerId(long id) {
        return carDAO.findByOwnerId(id);
    }

    @Override
    public List<Car> getNoOwnerCars(long id) {
        return carDAO.findNotOwnerCars(id);
    }
}
