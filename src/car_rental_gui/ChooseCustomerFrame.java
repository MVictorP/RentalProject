package car_rental_gui;

import car_rental_db_connect.Controller;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

public class ChooseCustomerFrame extends javax.swing.JFrame {

    protected EntityManager entityManager;
    private final Controller controller;

    private String searchText = "";

    public ChooseCustomerFrame(Controller controller) {
        this.entityManager = controller.connectDatabase();
        this.controller = controller;
        initComponents();

    }

    private void initComponents() {

        custSearchField = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        rentCarBtn = new javax.swing.JButton();
        rentedCarsBtn = new javax.swing.JButton();
        customersScrollPane = new javax.swing.JScrollPane();
        customersTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //buttons
        searchBtn.setText("Search");
        searchBtn.addActionListener((java.awt.event.ActionEvent evt) -> {
            searchBtnActionPerformed(evt);
        });

        rentCarBtn.setText("Rent Car");
        rentCarBtn.addActionListener((java.awt.event.ActionEvent evt) -> {
            rentCarBtnActionPerformed(evt);
        });
        rentedCarsBtn.setText("Rented Cars");
        rentedCarsBtn.addActionListener((java.awt.event.ActionEvent evt) -> {
            rentedCarBtnActionPerformed(evt);
        });
        //buttons

        //layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(custSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(searchBtn))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(rentCarBtn)
                                                .addGap(18, 18, 18)
                                                .addComponent(rentedCarsBtn))
                                        .addComponent(customersScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(custSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchBtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rentedCarsBtn)
                                        .addComponent(rentCarBtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(customersScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(29, Short.MAX_VALUE))
        );
        //layout

        pack();
    }

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {
        populateCustomersTable();
    }

    private void rentCarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int custID = (int) customersTable.getValueAt(customersTable.getSelectedRow(), 0);
            controller.selectedCustomer(custID);
            this.setVisible(false);
            CustomerWindows custWindow = new CustomerWindows(controller, 0); //included an int that will be used to set which tab will be open
            custWindow.setVisible(true);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No customer selected.");
        }
    }

    private void rentedCarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int custID = (int) customersTable.getValueAt(customersTable.getSelectedRow(), 0);
            controller.selectedCustomer(custID);
            this.setVisible(false);
            CustomerWindows custWindow = new CustomerWindows(controller, 1); //included an int that will be used to set which tab will be open. 1 = customer rentals
            custWindow.setVisible(true);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No customer selected.");
        }

    }

    private void populateCustomersTable() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();
        searchText = this.custSearchField.getText();
        customerList = controller.getCustomersList(searchText);
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customerList, customersTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${custID}"));
        columnBinding.setColumnName("Cust ID");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${telephone}"));
        columnBinding.setColumnName("Telephone");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${address}"));
        columnBinding.setColumnName("Address");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        customersScrollPane.setViewportView(customersTable);
        bindingGroup.bind();
    }

    private javax.swing.JTextField custSearchField;
    private javax.swing.JScrollPane customersScrollPane;
    private javax.swing.JTable customersTable;
    private javax.swing.JButton rentCarBtn;
    private javax.swing.JButton rentedCarsBtn;
    private javax.swing.JButton searchBtn;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    private java.util.List<car_rental_db_connect.Customer> customerList;

}
