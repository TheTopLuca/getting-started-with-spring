package Application.Project.boundry;

import Application.Project.entity.Car;
import Application.Project.entity.Manufacturer;
import Application.Project.services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/manufacturer-api")
public class ManufacturerController {

    @Autowired
    ManufacturerService service;

    @GetMapping("/getAll")
    public List<Manufacturer> getAll(){
        return service.getAllManufacturers();
    }

    @PostMapping("/add")
    public ResponseEntity<Manufacturer> addManufacturer(@RequestBody Manufacturer manufacturer){
        service.addManufacturer(manufacturer);
        return ResponseEntity.status(HttpStatus.OK).body(manufacturer);
    }
}
