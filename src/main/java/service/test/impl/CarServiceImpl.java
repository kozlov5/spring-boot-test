package service.test.impl;

import entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.test.CarService;
import service.test.OwnerService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private List<Car> cars = new ArrayList<>();

    @Autowired
    private OwnerService ownerService;

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public List<Car> getCars() {
        return cars;
    }

    @Override
    public List<Car> create(Car car) {
        if (ownerService.checkId(car.getOwnerId())){
            car.setId(cars.size() + 1);
            cars.add(car);
        }
        return cars;
    }

    @Override
    public List<Car> delete(long id) {
        if (checkId(id)) {
            cars = cars.stream().filter(f -> f.getId() != id).collect(Collectors.toList());
        }
        return cars;
    }

    @Override
    public List<Car> search(String keyword, String sort) {
        String finalKeyword = keyword.toLowerCase();
        List<Car> searchResult = cars.stream().filter(f ->
                f.getName().toLowerCase().contains(finalKeyword)
                || f.getModel().toLowerCase().contains(finalKeyword)
                || (f.getName().toLowerCase() + " " + f.getModel().toLowerCase()).contains(finalKeyword)
        ).collect(Collectors.toList());
        if (sort.equals("down")) {
            searchResult.sort((a, b) -> (b.getName() + " " + b.getModel()).compareTo(a.getName() + " " + a.getModel()));

        } else if(sort.equals("up")) {
            searchResult.sort(Comparator.comparing(a -> (a.getName() + " " + a.getModel())));

        }
        return searchResult;
    }

    @Override
    public List<Car> edit(long id, Car car) {
        if (checkId(id)) {
            Car result = cars.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
            result.setModel(car.getModel());
            result.setName(car.getName());
        }
        return cars;
    }

    @Override
    public boolean checkId(long id) {
        return cars.stream().filter(f -> f.getId() == id).collect(Collectors.toList()).size() > 0;
    }

    @Override
    public List<Car> getCarsByOwnerId(long id) {
        return cars.stream().filter(f -> f.getOwnerId() == id).collect(Collectors.toList());
    }

    @Override
    public Car getById(long id) {
        return cars.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
    }
}
