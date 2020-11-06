package Application.Project.services;

import Application.Project.control.CarRepo;
import Application.Project.control.ManufacturerRepo;
import Application.Project.entity.Car;
import Application.Project.entity.Manufacturer;
import Application.Project.exceptions.InvalidInputException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    @Autowired
    CarRepo carRepo;

    @Autowired
    ManufacturerService manufacturerService;

    public void modifyCar(Car car) throws NotFoundException {

        validateCar(car);
        Car carFromDb = carRepo.getCarById(car.getId());
        if(carFromDb == null) {
            throw  new InvalidInputException("No Car with such id exists");
        }
        carFromDb.setName(car.getName());
        carFromDb.setColor(car.getColor());
        carFromDb.setModel(car.getModel());
        carFromDb.setOwner(car.getOwner());
        carFromDb.setManufacturer(car.getManufacturer());
        carRepo.updateCar(carFromDb);
    }

    public List<Car> getAllCars() {
        return carRepo.getAllCars();
    }

    public void addNewCar(Car car) throws InvalidInputException, NotFoundException {

        validateCar(car);
        try {
            carRepo.insertCar(car);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCarById(long carId) throws NotFoundException {
        Car car = carRepo.getCarById(carId);
        if (car == null) {
            throw new NotFoundException("No Car could be found with that Id");
        } else {
            carRepo.deleteCar(carId);
        }
    }

    public void validateCar(Car car) throws InvalidInputException, NotFoundException {

        if (car.getName() == null) {
            throw new InvalidInputException("Car Name Can not be null!");
        }
        if (car.getManufacturer() == null) {
            throw new InvalidInputException("Car Manufactuer Can not be null!");
        }
        Manufacturer manufacturer = manufacturerService.getManufacturerById(car.getManufacturer().getId());
        if (!manufacturer.getName().equals(car.getManufacturer().getName())) {
            throw new InvalidInputException("Different Manufacturer Name expected with the given id , expected: " + manufacturer.getName() + " found :" + car.getManufacturer().getName());
        }
    }
    public List<Car> getCarByOwner(String owner){
        if(owner == null || owner.trim().isEmpty()){
            throw new InvalidInputException("Owner must have a name");
        }
        return carRepo.getCarsByOwner(owner);
    }
}
