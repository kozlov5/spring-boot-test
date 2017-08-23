package service.test;

import entity.Car;

import java.util.List;

public interface CarService {
    List<Car> getCars();
    List getCarsByOwnerId(long id);
    Car getById(long id);
    List<Car> create(Car car);
    List<Car> delete(long id);
    List<Car> search(String keyword, String sort);
    List<Car> edit(long id, Car car);
    boolean checkId(long id);
}
