package service.test;

import data.dto.CarDTO;
import entity.Car;

import java.util.List;

public interface CarService {
    List<CarDTO> getCars();
    Car getById(long id);
    Car create(Car car);
    String delete(long id);
    Car edit(Car car);
}
