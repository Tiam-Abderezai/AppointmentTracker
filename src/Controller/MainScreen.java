package Controller;

import Database.AppointmentCRUD;
import Database.CustomerCRUD;
import Model.Appointment;
import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainScreen implements Initializable {

    private static Customer selectedCustomer;
    private static Appointment selectedAppointment;

    @FXML
    public TableColumn<Customer, Integer> CustomerColumnID;
    public TableColumn<Customer, String> customerNameColumn;
    public TableColumn<Customer, String> customerAddressColumn;
    public TableColumn<Customer, String> customerCountryColumn;
    public TableColumn<Customer, String> customerCityColumn;
    public TableColumn<Customer, String> customerPhoneColumn;

    @FXML
    public TableColumn<Appointment, Integer> AppointmentColumnID;
//    public TableColumn<Appointment, String> AppointmentColumnCustomer;
    public TableColumn<Appointment, String> appointmentStartColumn;
    public TableColumn<Appointment, String> appointmentEndColumn;
    public TableColumn<Appointment, String> appointmentTypeColumn;

    @FXML
    public Button toCustomerMenu;

    @FXML
    public TableView<Customer> CustomerTable;
    @FXML
    public TableView<Appointment> AppointmentTable;
    @FXML
    private TableColumn<?, ?> appointmentCustomerColumn;
    @FXML
    private Button deleteCustomer;
    @FXML
    private Button editCustomerData;
    @FXML
    private Button editAppointment;
    @FXML
    private Button addAppointment;

// WARNING!!! DO NOT UNCOMMENT!!!: Used for debugging purposes
//    private String name;
//    private String address;
//    private String country;
//    private String city;
//    private String phone;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Cust. Obvs.List used with CRUD method to get all customers from DB 
        customerList = CustomerCRUD.getAllCustomers();

        // Lambda Exp. used to pull all data from Cust. DB
        customerNameColumn.setCellValueFactory(cellData -> {
            return new ReadOnlyStringWrapper(cellData.getValue().getName());
        }
        );
        customerAddressColumn.setCellValueFactory(cellData -> {
            return new ReadOnlyStringWrapper(cellData.getValue().getAddress());
        }
        );
        customerCountryColumn.setCellValueFactory(cellData -> {
            return new ReadOnlyStringWrapper(cellData.getValue().getCountry());
        }
        );
        customerCityColumn.setCellValueFactory(cellData -> {
            return new ReadOnlyStringWrapper(cellData.getValue().getCity());
        }
        );
        customerPhoneColumn.setCellValueFactory(cellData -> {
            return new ReadOnlyStringWrapper(cellData.getValue().getPhone());
        }
        );

        // Sets the customerList() items for the Cust.Table 
        // to display all saved Cust. data to the user
        CustomerTable.setItems(customerList);

// WARNING!!! DO NOT UNCOMMENT!!!: Used for debugging purposes 
//        customerAddressColumn.setCellValueFactory(("address"));
//        customerCountryColumn.setCellValueFactory(("country"));
//        customerCityColumn.setCellValueFactory(("city"));
//        customerPhoneColumn.setCellValueFactory(("phone"));
//        observableList.add(new Customer("Bob", "bob@email.com", 5551212));       
        // Cust. Obvs.List used with CRUD method to get all customers from DB 
        appointmentList = AppointmentCRUD.getAllAppointments();

        // Lambda Exp. used to pull all data from Appt. DB
        appointmentStartColumn.setCellValueFactory(cellData -> {
            return new ReadOnlyStringWrapper(cellData.getValue().getDateStart());
        }
        );
        appointmentEndColumn.setCellValueFactory(cellData -> {
            return new ReadOnlyStringWrapper(cellData.getValue().getDateEnd());
        }
        );
        appointmentTypeColumn.setCellValueFactory(cellData -> {
            return new ReadOnlyStringWrapper(cellData.getValue().getType());
        }
        );

        // Sets the customerList() items for the Cust.Table 
        // to display all saved Cust. data to the user
        AppointmentTable.setItems(appointmentList);

// WARNING!!! DO NOT UNCOMMENT!!!: Used for debugging purposes 
//        customerAddressColumn.setCellValueFactory(("address"));
//        customerCountryColumn.setCellValueFactory(("country"));
//        customerCityColumn.setCellValueFactory(("city"));
//        customerPhoneColumn.setCellValueFactory(("phone"));
//        observableList.add(new Customer("Bob", "bob@email.com", 5551212));       
    }

    public static ObservableList<Customer> customerList = FXCollections.observableArrayList();
    public static ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

    @FXML
    private void toCustomerMenu(ActionEvent event) throws IOException {
        Parent loadParentMain = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
        Scene loadSceneMain = new Scene(loadParentMain);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loadSceneMain);
        window.show();
    }

    @FXML
    private void addAppointment(ActionEvent event) throws IOException {
        selectedCustomer = CustomerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            System.out.println("Selected Customer is NULL");
            return;
        }
        Parent loadParentMain = FXMLLoader.load(getClass().getResource("/View/AddAppointment.fxml"));
        Scene loadSceneMain = new Scene(loadParentMain);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loadSceneMain);
        window.show();
    }

    @FXML
    private void editCustomer(ActionEvent event) throws IOException {
        selectedCustomer = CustomerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            System.out.println("Selected Customer is NULL");
            return;
        }

        Parent loadParentMain = FXMLLoader.load(getClass().getResource("/View/EditCustomer.fxml"));
        Scene loadSceneMain = new Scene(loadParentMain);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loadSceneMain);
        window.show();
    }

    @FXML
    private void editAppointment(ActionEvent event) throws IOException {
        selectedAppointment = AppointmentTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            System.out.println("Selected Appointment is NULL");
            return;
        }

        Parent loadParentMain = FXMLLoader.load(getClass().getResource("/View/EditAppointment.fxml"));
        Scene loadSceneMain = new Scene(loadParentMain);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loadSceneMain);
        window.show();
    }

    private void backToMain(ActionEvent event) throws IOException {

        Parent loadParentMain = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        Scene loadSceneMain = new Scene(loadParentMain);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loadSceneMain);
        window.show();

    }

    public static Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public static Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    @FXML
    private void deleteCustomer(ActionEvent event) throws Exception {
        selectedCustomer = CustomerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            System.out.println("Selected Customer is NULL");
            return;
        }
        
        CustomerCRUD.deleteCustomer(selectedCustomer.getCustomerId());
        MainScreen.customerList.remove(selectedCustomer);
//        CustomerTable.getItems().clear();
        CustomerTable.setItems(customerList);
        

    }

}
