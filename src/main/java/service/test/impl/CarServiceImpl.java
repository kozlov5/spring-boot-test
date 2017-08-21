package service.test.impl;

import entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.test.CarService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private List<Car> cars = new ArrayList<>();

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public List<Car> getCars() {
        return cars;
    }

    @Override
    public List<Car> create(Car car) {
        cars.add(car);
        return cars;
    }

    @Override
    public List<Car> delete(int id) {
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
    public List<Car> edit(int id, Car car) {
        if (checkId(id)) {
            Car result = cars.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
            result = car;
        }
        return cars;
    }

    @Override
    public boolean checkId(int id) {
        return cars.stream().filter(f -> f.getId() == id).collect(Collectors.toList()).size() > 0;
    }

}
