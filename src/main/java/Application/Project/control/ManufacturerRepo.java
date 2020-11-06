package Application.Project.control;

import Application.Project.entity.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Repository
public class ManufacturerRepo {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public Manufacturer getManufacturerById(long id){
        try {
            return (Manufacturer) em.createNamedQuery("getManufactuerById").setParameter("id",id).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    @Transactional
    public List<Manufacturer> getAll(){
        return em.createNamedQuery("getAllManufacturers").getResultList();
    }

    @Transactional
    public void addManufacturer(Manufacturer manufacturer){
        em.persist(manufacturer);
    }
}
