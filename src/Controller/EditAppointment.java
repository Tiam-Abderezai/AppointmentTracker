package Controller;

import Database.AppointmentCRUD;
import Model.Appointment;
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

public class EditAppointment implements Initializable {

    private static Appointment selectedAppointment;

    @FXML
    private TextField dateStartTextField;
    @FXML
    private TextField dateEndTextField;
    @FXML
    private TextField typeTextField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedAppointment = MainScreen.getSelectedAppointment();
//        nameTextField.setText(selectedAppointment.getName());
        dateStartTextField.setText(selectedAppointment.getDateStart());
        dateEndTextField.setText(selectedAppointment.getDateEnd());
        typeTextField.setText(selectedAppointment.getType());
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
        MainScreen.appointmentList.add(appointment);
        Parent loadParentMain = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        Scene loadSceneMain = new Scene(loadParentMain);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loadSceneMain);
        window.show();
    }
}
