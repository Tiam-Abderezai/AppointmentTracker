package Controller;

import Model.Appointment;
import Database.AppointmentCRUD;
import Model.Customer;
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

public class AddAppointment implements Initializable {

    private static Customer selectedCustomer;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField dateStartTextField;
    @FXML
    private TextField dateEndTextField;
    @FXML
    private TextField typeTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedCustomer = MainScreen.getSelectedCustomer();
        nameTextField.setText(selectedCustomer.getName());
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
    private void saveAppointment(ActionEvent event) throws IOException, Exception {
        Appointment appointment = AppointmentCRUD.addAppointment(dateStartTextField.getText(), dateEndTextField.getText(), typeTextField.getText());
        Parent loadParentMain = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        Scene loadSceneMain = new Scene(loadParentMain);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loadSceneMain);
        window.show();
    }
}
