package service.test;

import entity.Car;

import java.util.List;

public interface CarService {
    List<Car> getCars();
    Car getById(long id);
    Car create(Car car);
    String delete(long id);
    Car edit(Car car);
}
