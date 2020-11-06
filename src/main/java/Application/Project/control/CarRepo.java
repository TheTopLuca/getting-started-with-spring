package Application.Project.control;

import Application.Project.entity.Car;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CarRepo {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public List<Car> getAllCars() {
        return em.createNamedQuery("getAllCars").getResultList();
    }


    @Transactional
    public Car getCarById(long id) {
        try {
            return  (Car)
                    em.createNamedQuery("getCarById").setParameter("id", id).getSingleResult();

        } catch (NoResultException e) {
            return null;
        }

    }

    @Transactional
    public Car getCarByManufacturerId(long id) {
        return (Car) em.createNamedQuery("getCarByManufacturerId").setParameter("id", id).getSingleResult();
    }

    @Transactional
    public void updateCar(Car car) {
        em.merge(car);
    }

    @Transactional
    public void deleteCar(long carId) {
        Car car = getCarById(carId);
        if (car != null) {
            em.remove(car);
        }
    }

    @Transactional
    public void insertCar(Car car) {
        em.persist(car);
    }

    @Transactional
    public List<Car> getCarsByOwner(String owner) {
        return em.createNamedQuery("getCarsByOwner").setParameter("owner", owner).getResultList();
    }


}
