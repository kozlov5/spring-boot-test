package service.test;

import entity.Car;

import java.util.List;

public interface CarService {
    List<Car> getCars();
    List<Car> getCarsByOwnerId(int id);
    Car getById(int id);
    List<Car> create(Car car);
    List<Car> delete(int id);
    List<Car> search(String keyword, String sort);
    List<Car> edit(int id, Car car);
    boolean checkId(int id);
}
