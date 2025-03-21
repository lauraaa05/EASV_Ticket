package controllers;

import bll.UserManagement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EvetMainController {
    @FXML
    private Button logoutButtonEC;

    private final UserManagement userManagement = new UserManagement();

    @FXML
    private void initialize() {
        logoutButtonEC.setOnAction(event -> logoutMainScreen());
    }

    private void switchScene(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) logoutButtonEC.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void logoutMainScreen() {
        switchScene("LoginMain.fxml", "Login Screen");
    }
}