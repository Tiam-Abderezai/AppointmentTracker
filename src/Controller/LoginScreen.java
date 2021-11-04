/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import Database.UserCRUD;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Tiam
 */
public class LoginScreen implements Initializable {

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassWord;
    @FXML
    private Label label;
    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void loginHandler(ActionEvent event) throws IOException {
        // Define username and passwords to be pulled from text fields  
        User user = UserCRUD.getUser(txtUserName.getText(), txtPassWord.getText());
        if (user != null) {
            // If true then open MainScreen.fxml
            System.out.println("SUCCESS");
            Parent loadParentMain = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
            Scene loadSceneMain = new Scene(loadParentMain);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(loadSceneMain);
            window.show();
        } else {
            // else if false, then give user-error message (try again)
            System.out.println("Wrong credentials.");
        }
    }
}
