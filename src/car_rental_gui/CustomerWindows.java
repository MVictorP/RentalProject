package car_rental_gui;

import car_rental_db_connect.Controller; //everything done through controller

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import javax.swing.JOptionPane;

public class CustomerWindows extends javax.swing.JFrame {

    private final Controller controller;
    private final int index; //to set which tab to show

    private SimpleDateFormat dateFormat;
    private String searchText = "";

    public CustomerWindows(Controller controller, int index) {
        this.controller = controller;
        this.index = index; //value is determined by which button you clicked and determine which tab you see when this Frame is loaded
        initComponents();

    }

    private void initComponents() {
        topTabsPane = new javax.swing.JTabbedPane();

        searchCarsTextField = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        rentSelectedBtn = new javax.swing.JButton();
        findCarPanel = new javax.swing.JPanel();
        availableCarsScrollPane = new javax.swing.JScrollPane();
        availableCarsTable = new javax.swing.JTable();

        returnSelectedBtn = new javax.swing.JButton();
        rentedCarsPanel = new javax.swing.JPanel();
        rentedCarsScrollPane = new javax.swing.JScrollPane();
        rentedCarsTable = new javax.swing.JTable();

        returnedCarsPanel = new javax.swing.JPanel();
        returnedCarsScrollPane = new javax.swing.JScrollPane();
        returnedCarsTable = new javax.swing.JTable();

        customerName = new javax.swing.JLabel();

        rentSelectedBtn.setToolTipText("Hold CTRL Button to make multiple selections.");
        returnSelectedBtn.setToolTipText("Hold CTRL Button to make multiple selections.");

        customerName.setText(controller.getCustomerName() + "'s Account");

        dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        populateReturnedCarsTable();
        populateRentedCarsTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //buttons...
        searchBtn.setText("Search");
        searchBtn.addActionListener((java.awt.event.ActionEvent evt) -> {
            searchBtnActionPerformed(evt);
        });

        rentSelectedBtn.setText("Rent Selected");
        rentSelectedBtn.addActionListener((java.awt.event.ActionEvent evt) -> {
            rentSelectedBtnActionPerformed(evt);
        });
        returnSelectedBtn.setText("Return Selected");
        returnSelectedBtn.addActionListener((java.awt.event.ActionEvent evt) -> {
            returnSelectedBtnActionPerformed(evt);
        });
        //...buttons

        //layout...
        //I arranged everything using GUI builder then removed the code-locks
        javax.swing.GroupLayout findCarPanelLayout = new javax.swing.GroupLayout(findCarPanel);
        findCarPanel.setLayout(findCarPanelLayout);
        findCarPanelLayout.setHorizontalGroup(
                findCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(findCarPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(findCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(findCarPanelLayout.createSequentialGroup()
                                                .addComponent(searchCarsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(searchBtn))
                                        .addComponent(rentSelectedBtn)
                                        .addComponent(availableCarsScrollPane))
                                .addContainerGap(30, Short.MAX_VALUE))
        );
        findCarPanelLayout.setVerticalGroup(
                findCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(findCarPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(findCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(searchBtn)
                                        .addComponent(searchCarsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(rentSelectedBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(availableCarsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(13, Short.MAX_VALUE))
        );

        topTabsPane.addTab("Find Car", findCarPanel);

        javax.swing.GroupLayout rentedCarsPanelLayout = new javax.swing.GroupLayout(rentedCarsPanel);
        rentedCarsPanel.setLayout(rentedCarsPanelLayout);
        rentedCarsPanelLayout.setHorizontalGroup(
                rentedCarsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rentedCarsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(rentedCarsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(returnSelectedBtn)
                                        .addComponent(rentedCarsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(17, Short.MAX_VALUE))
        );
        rentedCarsPanelLayout.setVerticalGroup(
                rentedCarsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rentedCarsPanelLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(returnSelectedBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rentedCarsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(16, Short.MAX_VALUE))
        );

        topTabsPane.addTab("Rented Cars", rentedCarsPanel);

        javax.swing.GroupLayout returnedCarsPanelLayout = new javax.swing.GroupLayout(returnedCarsPanel);
        returnedCarsPanel.setLayout(returnedCarsPanelLayout);
        returnedCarsPanelLayout.setHorizontalGroup(
                returnedCarsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(returnedCarsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(returnedCarsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                                .addContainerGap())
        );
        returnedCarsPanelLayout.setVerticalGroup(
                returnedCarsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, returnedCarsPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(returnedCarsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(122, 122, 122))
        );

        topTabsPane.addTab("Returned Cars", returnedCarsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(customerName)
                                        .addComponent(topTabsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(customerName)
                                .addGap(18, 18, 18)
                                .addComponent(topTabsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(14, Short.MAX_VALUE))
        );
        topTabsPane.setSelectedIndex(index);
        //...layout

        pack();
    }

    //buttons actions...
    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {
        populateAvailableCarsTable();
    }

    //Allows for multiple rows to be selected
    //The user will need to hold the CTRL button (to selecte more than one row) 
    //and are notified of this feature when they mouse over the button (line 50)
    private void returnSelectedBtnActionPerformed(java.awt.event.ActionEvent evt) {
        boolean hasReturned = false; //aid in error handling
        try {
            if (rentedCarsTable.getRowCount() > 0) {
                if (rentedCarsTable.getSelectedRowCount() > 0) {
                    String dateStr = JOptionPane.showInputDialog(null, "Enter Return Date 'MM/dd/yyyy'");
                    Date date = dateFormat.parse(dateStr);
                    int selectedRow[] = rentedCarsTable.getSelectedRows();
                    for (int i : selectedRow) {
                        int rentalID = (int) rentedCarsTable.getValueAt(i, 0);
                        controller.selectedCarForReturn(rentalID, date);
                    }
                    //refresh all the tables to reflect the new return
                    populateReturnedCarsTable();
                    populateRentedCarsTable();
                    populateAvailableCarsTable();
                    hasReturned = true;
                }
                if (rentedCarsTable.getSelectedRowCount() == 0 && hasReturned == false) {
                    JOptionPane.showMessageDialog(null, "Select a car to return.");

                }
            }
        } catch (Exception e) {
            if (e instanceof ParseException) {
                JOptionPane.showMessageDialog(null, "Invalid Date.");
            } else if (e instanceof ArrayIndexOutOfBoundsException) {
                JOptionPane.showMessageDialog(null, "Select a car to return.");
            }
        }
        if (rentedCarsTable.getRowCount() == 0 && hasReturned == false) {
            JOptionPane.showMessageDialog(null, "You have no rentals for return.");
        }

    }

    //Allows for multiple rows to be selected
    //The user will need to hold the CTRL button (to selecte more than one row) 
    //and are notified of this feature when they mouse over the button (line 49)
    private void rentSelectedBtnActionPerformed(java.awt.event.ActionEvent evt) {
        boolean hasRented = false;
        try {
            if (availableCarsTable.getRowCount() > 0) {
                if (availableCarsTable.getSelectedRowCount() > 0) {
                    String dateStr = JOptionPane.showInputDialog(null, "Enter Rent Date 'MM/dd/yyyy'");
                    Date date = dateFormat.parse(dateStr);
                    int selectedRow[] = availableCarsTable.getSelectedRows();
                    for (int i : selectedRow) {
                        int carID = (int) availableCarsTable.getValueAt(i, 0);
                        controller.selectedCarForRent(carID, date);
                    }

                    //refresh all the tables to reflect the new rental
                    populateRentedCarsTable();
                    populateAvailableCarsTable();
                    hasRented = true; //user has successfully rented

                }
                //error handling; shows error if no rows are selected and the user
                //has not rented
                if (availableCarsTable.getSelectedRowCount() == 0 && hasRented == false) {
                    JOptionPane.showMessageDialog(null, "Select a car to rent.");
                }

            }

        } catch (Exception e) {
            if (e instanceof ParseException) {
                JOptionPane.showMessageDialog(null, "Invalid Date.");
            } else if (e instanceof ArrayIndexOutOfBoundsException) {
                JOptionPane.showMessageDialog(null, "Select a car to rent.");
            }
        }
        //error handling: shows error if rentSelected button is pressed and
        //there are no results in the table 
        if (availableCarsTable.getRowCount() == 0 && hasRented == false) {
            JOptionPane.showMessageDialog(null, "You must search for a car before renting.");
        }

    }
    //...buttons actions

    //populate table functions...
    private void populateReturnedCarsTable() {
        ReturnedCarsBindingGroup = new org.jdesktop.beansbinding.BindingGroup();
        returnedCarsList = controller.getReturnedCarsList();
        org.jdesktop.swingbinding.JTableBinding jTableBinding2 = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, returnedCarsList, returnedCarsTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding2 = jTableBinding2.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${carID}"));
        columnBinding2.setColumnName("Car ID");
        columnBinding2.setColumnClass(Integer.class);
        columnBinding2.setEditable(false);
        columnBinding2 = jTableBinding2.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${make}"));
        columnBinding2.setColumnName("Make");
        columnBinding2.setColumnClass(String.class);
        columnBinding2.setEditable(false);
        columnBinding2 = jTableBinding2.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${model}"));
        columnBinding2.setColumnName("Model");
        columnBinding2.setColumnClass(String.class);
        columnBinding2.setEditable(false);
        columnBinding2 = jTableBinding2.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${year}"));
        columnBinding2.setColumnName("Year");
        columnBinding2.setColumnClass(String.class);
        columnBinding2.setEditable(false);
        columnBinding2 = jTableBinding2.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${rentDate}"));
        columnBinding2.setColumnName("Rented");
        columnBinding2.setColumnClass(Date.class);
        columnBinding2.setEditable(false);
        columnBinding2 = jTableBinding2.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${returnDate}"));
        columnBinding2.setColumnName("Returned");
        columnBinding2.setColumnClass(Date.class);
        columnBinding2.setEditable(false);
        ReturnedCarsBindingGroup.addBinding(jTableBinding2);
        jTableBinding2.bind();
        returnedCarsScrollPane.setViewportView(returnedCarsTable);
    }

    private void populateRentedCarsTable() {
        RentedCarsBindingGroup = new org.jdesktop.beansbinding.BindingGroup();
        rentedCarsList = controller.getRentedCarsList();
        org.jdesktop.swingbinding.JTableBinding jTableBinding1 = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rentedCarsList, rentedCarsTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding1 = jTableBinding1.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${rentalID}"));
        columnBinding1.setColumnName("Rental ID");
        columnBinding1.setColumnClass(Integer.class);
        columnBinding1.setEditable(false);
        columnBinding1 = jTableBinding1.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${make}"));
        columnBinding1.setColumnName("Make");
        columnBinding1.setColumnClass(String.class);
        columnBinding1 = jTableBinding1.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${model}"));
        columnBinding1.setColumnName("Model");
        columnBinding1.setColumnClass(String.class);
        columnBinding1.setEditable(false);
        columnBinding1 = jTableBinding1.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${year}"));
        columnBinding1.setColumnName("Year");
        columnBinding1.setColumnClass(String.class);
        columnBinding1.setEditable(false);
        columnBinding1 = jTableBinding1.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${rentDate}"));
        columnBinding1.setColumnName("Rented");
        columnBinding1.setColumnClass(Date.class);
        columnBinding1.setEditable(false);
        RentedCarsBindingGroup.addBinding(jTableBinding1);
        jTableBinding1.bind();
        rentedCarsScrollPane.setViewportView(rentedCarsTable);
    }

    private void populateAvailableCarsTable() {
        AvailableCarsBindingGroup = new org.jdesktop.beansbinding.BindingGroup();
        searchText = this.searchCarsTextField.getText();
        rentableCarsList = controller.getAvailableCarsList(searchText);
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rentableCarsList, availableCarsTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${carID}"));
        columnBinding.setColumnName("Car ID");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${make}"));
        columnBinding.setColumnName("Make");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${model}"));
        columnBinding.setColumnName("Model");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${year}"));
        columnBinding.setColumnName("Year");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${size}"));
        columnBinding.setColumnName("Size");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        AvailableCarsBindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        availableCarsScrollPane.setViewportView(availableCarsTable);
    }
    //...populate table functions

    private javax.swing.JScrollPane availableCarsScrollPane;
    private javax.swing.JTable availableCarsTable;
    private javax.swing.JLabel customerName;
    private javax.swing.JPanel findCarPanel;
    private javax.swing.JTabbedPane topTabsPane;
    private javax.swing.JButton rentSelectedBtn;
    private javax.swing.JPanel rentedCarsPanel;
    private javax.swing.JScrollPane rentedCarsScrollPane;
    private javax.swing.JTable rentedCarsTable;
    private javax.swing.JButton returnSelectedBtn;
    private javax.swing.JPanel returnedCarsPanel;
    private javax.swing.JScrollPane returnedCarsScrollPane;
    private javax.swing.JTable returnedCarsTable;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchCarsTextField;
    private org.jdesktop.beansbinding.BindingGroup AvailableCarsBindingGroup;
    private org.jdesktop.beansbinding.BindingGroup RentedCarsBindingGroup;
    private org.jdesktop.beansbinding.BindingGroup ReturnedCarsBindingGroup;
    private java.util.List<car_rental_db_connect.Rental> rentableCarsList;
    private java.util.List<car_rental_db_connect.Rental> rentedCarsList;
    private java.util.List<car_rental_db_connect.Rental> returnedCarsList;

}
