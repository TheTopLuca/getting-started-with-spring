package Application.Project.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CAR")
@NamedQueries({
        @NamedQuery(name = "getAllCars" ,query = "SELECT c FROM Car c") ,
        @NamedQuery(name = "getCarById" , query = "SELECT c FROM Car c where c.id = :id"),
        @NamedQuery(name = "getCarsByOwner" , query = "Select c from Car c where c.owner = :owner"),
        @NamedQuery(name = "getCarByManufacturerId" , query = "SELECT c FROM Car c where c.manufacturer = :id")
})

public class Car implements Serializable , Comparable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(name = "model")
    private String model;


    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;


//    @Column(name= "manufacturer_id" ,  insertable=true        , updatable = false)
//    private Long manufacturerId ;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "owner")
    private String owner;

    @Column(name = "mileage")
    private Double mileage;

    public Car() {
    }


    public long getId() {
        return id;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    private Double getMileage() {
        return mileage;
    }

    private void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return " Car { id : " + id + " , name : " + name +" , model : " + model + " , owner : " + owner + " , color : " + color +
        " , manufacturer : " + manufacturer.getName() + " }";
    }


    @Override
    public int compareTo(Object o) {
        Car car = (Car) o;
        return this.getName().toLowerCase().compareToIgnoreCase(car.getName().toLowerCase());
    }
}
