package Application.Project.entity;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MANUFACTURER")
@NamedQuery(name = "getManufactuerById",query = "Select m from Manufacturer m where m.id =:id")
@NamedQuery(name = "getAllManufacturers" , query = "Select m from Manufacturer m")
public class Manufacturer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(name = "name")
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
