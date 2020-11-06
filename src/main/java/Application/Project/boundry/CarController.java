package Application.Project.boundry;

import Application.Project.entity.Car;
import Application.Project.exceptions.InvalidInputException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Application.Project.services.CarService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car-api")
public class CarController {

    @Autowired
    CarService service;

    @GetMapping("/getAll")
    public List<String> getAll(){
        List<Car> cars = service.getAllCars();
        List<String> result = new ArrayList<>();
        for(Car car : cars){
            result.add(car.toString());
        }
        return result;
    }

    @PostMapping("/addCar")
    public ResponseEntity<Car> insertCar(@RequestBody Car car) throws InvalidInputException, NotFoundException {
        service.addNewCar(car);
        return ResponseEntity.ok().body(car);
    }

    @PutMapping("/updateCar")
    public ResponseEntity<String> updateCar(@RequestBody Car car) throws InvalidInputException, NotFoundException {
        service.modifyCar(car);
        return ResponseEntity.ok().body("Updated Successfully");
    }

    @PostMapping("/deleteCar/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable("carId") long carId) throws NotFoundException {
        service.deleteCarById(carId);
        return ResponseEntity.ok().body("Successfully Deleted");
    }

    @GetMapping("/getAllOrderedByName")
    public ResponseEntity<List<Car>> getCarsOrderedByName(){
       List<Car> names =  service.getAllCars().stream().sorted().collect(Collectors.toList());
       return ResponseEntity.ok(names);
    }

    @GetMapping("/getCarsByOwnerName/{ownerName}")
    public ResponseEntity<List<Car>> getCarsByOwnerName(@PathVariable("ownerName") String name){
        return ResponseEntity.status(HttpStatus.OK).body(service.getCarByOwner(name));
    }

}
