/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import Model.Customer;
import Database.CustomerCRUD;
//import static appointmenttracker.MainScreen.CustomerTable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tiam
 */
public class AddCustomer implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField countryTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField phoneTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void backToMain(ActionEvent event) throws IOException {
        Parent loadParentMain = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        Scene loadSceneMain = new Scene(loadParentMain);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loadSceneMain);
        window.show();
    }

    @FXML
    private void saveCustomer(ActionEvent event) throws IOException, Exception {
        Customer customer = CustomerCRUD.addCustomer(nameTextField.getText(), addressTextField.getText(), countryTextField.getText(), cityTextField.getText(), phoneTextField.getText());
        MainScreen.customerList.add(customer);
        Parent loadParentMain = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        Scene loadSceneMain = new Scene(loadParentMain);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loadSceneMain);
        window.show();
    }
}
