
package car_rental_db_connect;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "rentable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rentable.findAll", query = "SELECT r FROM Rentable r")
    , @NamedQuery(name = "Rentable.findByCarID", query = "SELECT r FROM Rentable r WHERE r.carID = :carID")
    , @NamedQuery(name = "Rentable.findByMake", query = "SELECT r FROM Rentable r WHERE r.make = :make")
    , @NamedQuery(name = "Rentable.findByModel", query = "SELECT r FROM Rentable r WHERE r.model = :model")
    , @NamedQuery(name = "Rentable.findByYear", query = "SELECT r FROM Rentable r WHERE r.year = :year")
    , @NamedQuery(name = "Rentable.findBySize", query = "SELECT r FROM Rentable r WHERE r.size = :size")
    , @NamedQuery(name = "Rentable.findByStatus", query = "SELECT r FROM Rentable r WHERE r.status = :status")})
public class Rentable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "carID")
    @Id
    private int carID;
    @Basic(optional = false)
    @Column(name = "make")
    private String make;
    @Basic(optional = false)
    @Column(name = "model")
    private String model;
    @Basic(optional = false)
    @Column(name = "year")
    private String year;
    @Basic(optional = false)
    @Column(name = "size")
    private String size;
    @Column(name = "status")
    private String status;

    public Rentable() {
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
