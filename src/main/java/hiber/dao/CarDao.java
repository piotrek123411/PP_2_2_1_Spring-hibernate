package hiber.dao;

import hiber.model.Car;

import java.util.List;

public interface CarDao {
    void add(Car car);
    List<Car> listCars();
//    Car findCar(String car_name, String car_series);
}
