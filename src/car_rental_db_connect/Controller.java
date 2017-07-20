package car_rental_db_connect;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class Controller {

    private EntityManager em = null;
    private EntityManagerFactory emf = null;

    private Customer selectedCustomer;
    private Car selectedCarForRent;
    private Rental selectedReturn;

    private List<Customer> customerList;
    private List<Rental> availToRentList;
    private List<Rental> custRentalsList;
    private List<Rental> custReturnsList;

    private Query availToRentQuery;
    private Query custRentalsQuery;
    private Query custReturnsQuery;
    private Query customerQuery;

    private String searchText;

    private static Controller singleton;

    private Controller() {

    }

    public static Controller Instance() {
        if (singleton == null) {
            singleton = new Controller();
        }
        return singleton;
    }

    //establishes a connection to the database that will be used
    public EntityManager connectDatabase() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("car_rentalPU");
        }
        if (em == null) {
            em = emf.createEntityManager();
            return em;
        }
        return null;
    }

    //Create a new rental in the database.
    //Check to make sure the date entered is equal to or after than today's date
    public void selectedCarForRent(Integer carID, Date date) {
        String rented = "rented";

        try {
            //Keep only the month, day, year of the current date and set the 
            //format to match what is entered through the popup window
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date currentDateTime = new Date();
            Date currentDate = formatter.parse(formatter.format(currentDateTime));

            //check the date before creating the Rental
            if (date.after(currentDate) || date.equals(currentDate)) {
                selectedCarForRent = em.find(Car.class, carID);
                Rental newRental = new Rental();
                newRental.setCarID(selectedCarForRent);
                newRental.setCustID(selectedCustomer);
                newRental.setStatus(rented);
                newRental.setRentDate(date);

                //The Rental instance is constructed as an ordinary Java object 
                //and its initial state is New. An explicit call to persist associates 
                //the object with an owner EntityManager em and changes its 
                //state to Managed. The new entity object is stored 
                //in the database when the transaction is committed.
                //http://www.objectdb.com/java/jpa/persistence/store
                em.getTransaction().begin();
                em.persist(newRental);
                em.getTransaction().commit();
            }
            if (date.before(currentDate)) {
                JOptionPane.showMessageDialog(null, "Rental date is before today's date.\nTry Again.");
            }
        } catch (ParseException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //find a rental in the database and update it to returned
    //Check to make sure the date entered is after or equal to the day it was rented.
    public void selectedCarForReturn(Integer rentalID, Date date) {
        String returned = "returned";
        Date rentDate;

        //1)find the rental that the user selected
        //2)get its rental date
        //3)check to make sure the date the user entered as a return date
        //is after or equal to the rent date
        selectedReturn = em.find(Rental.class, rentalID);
        rentDate = selectedReturn.getRentDate();
        if (date.after(rentDate) || date.equals(rentDate)) {
            selectedReturn.setStatus(returned);
            selectedReturn.setReturnDate(date);

            //The Rental instance is constructed as an ordinary Java object 
            //and its initial state is New. An explicit call to persist associates 
            //the object with an owner EntityManager em and changes its 
            //state to Managed. The new entity object is stored 
            //in the database when the transaction is committed.
            //http://www.objectdb.com/java/jpa/persistence/store
            em.getTransaction().begin();
            em.persist(selectedReturn);
            em.getTransaction().commit();
        }
        if (date.before(rentDate)) {
            JOptionPane.showMessageDialog(null, "Return date is before rental date.\nTry Again.");
        }

    }

    //sets the currently selected customer, this will be used to get specific
    //tables relating to that customer only
    public void selectedCustomer(Integer custID) {
        this.selectedCustomer = em.find(Customer.class, custID);
    }

    //used to show name at the top of the window
    public String getCustomerName() {
        return this.selectedCustomer.getName();
    }

    //takes whatever customer was selected and gets their ID. Then takes that ID
    //and returns Rentals that have a matching customer ID. This was simplified
    //by creating a database view that contained only Rentals that have been rented.
    //This view also contains the columns/data that the JTable will need to show.
    //ex. make, model, year
    public List<Rental> getRentedCarsList() {
        int custID = selectedCustomer.getCustID();
        custRentalsQuery = java.beans.Beans.isDesignTime() ? null : em.createQuery("SELECT r FROM Rented r WHERE r.custID = :code");
        custRentalsQuery.setParameter("code", custID);
        custRentalsList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : custRentalsQuery.getResultList();
        return custRentalsList;
    }

    //takes whatever customer was selected and gets their ID. Then takes that ID
    //and returns Rentals that have a matching customer ID. This was simplified
    //by creating a database view that contained only Rentals that have been returned.
    //This view also contains the columns/data that the JTable will need to show.
    //ex. make, model, year, size
    public List<Rental> getReturnedCarsList() {
        int custID = selectedCustomer.getCustID();
        custReturnsQuery = java.beans.Beans.isDesignTime() ? null : em.createQuery("SELECT r FROM Returned r WHERE r.custID = :code");
        custReturnsQuery.setParameter("code", custID);
        custReturnsList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : custReturnsQuery.getResultList();
        return custReturnsList;
    }

    //takes the text that the user searched for and compares it to the data
    //in the database. If something in the row matches (make, model, size, year)
    //it is added to the list and that list is returned
    public List<Rental> getAvailableCarsList(String text) {
        this.searchText = text;
        availToRentQuery = java.beans.Beans.isDesignTime() ? null : em.createQuery("SELECT r FROM Rentable r WHERE CONCAT(r.make, r.model, r.size, r.year) LIKE :code");
        availToRentQuery.setParameter("code", "%" + this.searchText + "%");
        availToRentList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : availToRentQuery.getResultList();
        return availToRentList;
    }

    //takes the text that the user searched for and compares it to the data
    //in the database. If something in the row matches (name, telephone, address) 
    //it is added to the list and that list is returned
    public List<Customer> getCustomersList(String text) {
        this.searchText = text;
        customerQuery = java.beans.Beans.isDesignTime() ? null : em.createQuery("SELECT c FROM Customer c WHERE CONCAT(c.name, c.telephone, c.address) LIKE :code");
        customerQuery.setParameter("code", "%" + this.searchText + "%");
        customerList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : customerQuery.getResultList();
        return customerList;
    }

}
