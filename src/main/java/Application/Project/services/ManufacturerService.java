package Application.Project.services;

import Application.Project.control.ManufacturerRepo;
import Application.Project.entity.Manufacturer;
import Application.Project.exceptions.InvalidInputException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class ManufacturerService {

    @Autowired
    ManufacturerRepo repo;

    public List<Manufacturer> getAllManufacturers() {
        return repo.getAll();
    }

    public Manufacturer getManufacturerById(long mId) throws NotFoundException {
        try {
            Manufacturer manufacturer = repo.getManufacturerById(mId);
            if (manufacturer == null) {
                throw new NotFoundException("No Manufacturer with such Id exists");
            }
            return manufacturer;
        }
        catch (NoResultException e) {
            throw new NotFoundException("Can't find a manufacturer with the specified id");
        }
    }

    public void addManufacturer(Manufacturer manufacturer) {
        if (manufacturer.getName() == null) {
            throw new InvalidInputException("Manufacturer name can not be empty");
        }
        repo.addManufacturer(manufacturer);
    }
}
