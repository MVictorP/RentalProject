
package car_rental_db_connect;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "returned")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Returned.findAll", query = "SELECT r FROM Returned r")
    , @NamedQuery(name = "Returned.findByCarID", query = "SELECT r FROM Returned r WHERE r.carID = :carID")
    , @NamedQuery(name = "Returned.findByMake", query = "SELECT r FROM Returned r WHERE r.make = :make")
    , @NamedQuery(name = "Returned.findByModel", query = "SELECT r FROM Returned r WHERE r.model = :model")
    , @NamedQuery(name = "Returned.findByYear", query = "SELECT r FROM Returned r WHERE r.year = :year")
    , @NamedQuery(name = "Returned.findByRentDate", query = "SELECT r FROM Returned r WHERE r.rentDate = :rentDate")
    , @NamedQuery(name = "Returned.findByReturnDate", query = "SELECT r FROM Returned r WHERE r.returnDate = :returnDate")
    , @NamedQuery(name = "Returned.findByStatus", query = "SELECT r FROM Returned r WHERE r.status = :status")
    , @NamedQuery(name = "Returned.findByCustID", query = "SELECT r FROM Returned r WHERE r.custID = :custID")
    , @NamedQuery(name = "Returned.findByRentalID", query = "SELECT r FROM Returned r WHERE r.rentalID = :rentalID")})
public class Returned implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "carID")
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
    @Column(name = "rentDate")
    @Temporal(TemporalType.DATE)
    private Date rentDate;
    @Column(name = "returnDate")
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "custID")
    private int custID;
    @Basic(optional = false)
    @Column(name = "rentalID")
    @Id
    private int rentalID;

    public Returned() {
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

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public int getRentalID() {
        return rentalID;
    }

    public void setRentalID(int rentalID) {
        this.rentalID = rentalID;
    }
    
}
