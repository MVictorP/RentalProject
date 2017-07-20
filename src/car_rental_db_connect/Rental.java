
package car_rental_db_connect;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "rental")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rental.findAll", query = "SELECT r FROM Rental r")
    , @NamedQuery(name = "Rental.findByRentalID", query = "SELECT r FROM Rental r WHERE r.rentalID = :rentalID")
    , @NamedQuery(name = "Rental.findByRentDate", query = "SELECT r FROM Rental r WHERE r.rentDate = :rentDate")
    , @NamedQuery(name = "Rental.findByReturnDate", query = "SELECT r FROM Rental r WHERE r.returnDate = :returnDate")
    , @NamedQuery(name = "Rental.findByStatus", query = "SELECT r FROM Rental r WHERE r.status = :status")})
public class Rental implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rentalID")
    private Integer rentalID;
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
    @JoinColumn(name = "carID", referencedColumnName = "carID")
    @ManyToOne(optional = false)
    private Car carID;
    @JoinColumn(name = "custID", referencedColumnName = "custID")
    @ManyToOne(optional = false)
    private Customer custID;

    public Rental() {
    }

    public Rental(Integer rentalID) {
        this.rentalID = rentalID;
    }

    public Rental(Integer rentalID, Date rentDate, String status) {
        this.rentalID = rentalID;
        this.rentDate = rentDate;
        this.status = status;
    }

    public Integer getRentalID() {
        return rentalID;
    }

    public void setRentalID(Integer rentalID) {
        this.rentalID = rentalID;
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

    public Car getCarID() {
        return carID;
    }

    public void setCarID(Car carID) {
        this.carID = carID;
    }

    public Customer getCustID() {
        return custID;
    }

    public void setCustID(Customer custID) {
        this.custID = custID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rentalID != null ? rentalID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Rental)) {
            return false;
        }
        Rental other = (Rental) object;
        if ((this.rentalID == null && other.rentalID != null) || (this.rentalID != null && !this.rentalID.equals(other.rentalID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "car_rental_db_connect.Rental[ rentalID=" + rentalID + " ]";
    }
    
}
