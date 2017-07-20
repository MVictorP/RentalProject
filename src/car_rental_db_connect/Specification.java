
package car_rental_db_connect;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "specification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Specification.findAll", query = "SELECT s FROM Specification s")
    , @NamedQuery(name = "Specification.findBySpecID", query = "SELECT s FROM Specification s WHERE s.specID = :specID")
    , @NamedQuery(name = "Specification.findByMake", query = "SELECT s FROM Specification s WHERE s.make = :make")
    , @NamedQuery(name = "Specification.findByModel", query = "SELECT s FROM Specification s WHERE s.model = :model")
    , @NamedQuery(name = "Specification.findByYear", query = "SELECT s FROM Specification s WHERE s.year = :year")
    , @NamedQuery(name = "Specification.findBySize", query = "SELECT s FROM Specification s WHERE s.size = :size")})
public class Specification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "specID")
    private Integer specID;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "specID")
    private List<Car> carList;

    public Specification() {
    }

    public Specification(Integer specID) {
        this.specID = specID;
    }

    public Specification(Integer specID, String make, String model, String year, String size) {
        this.specID = specID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.size = size;
    }

    public Integer getSpecID() {
        return specID;
    }

    public void setSpecID(Integer specID) {
        this.specID = specID;
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

    @XmlTransient
    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (specID != null ? specID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Specification)) {
            return false;
        }
        Specification other = (Specification) object;
        if ((this.specID == null && other.specID != null) || (this.specID != null && !this.specID.equals(other.specID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "car_rental_db_connect.Specification[ specID=" + specID + " ]";
    }
    
}
