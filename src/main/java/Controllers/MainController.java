package Controllers;

import DAL.UserDAO;
import be.User;
import bll.EventManagement;
import bll.UserManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class MainController {

    UserManagement um = new UserManagement();
    UserDAO userDAO = new UserDAO();


    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField visiblePasswordField;

    @FXML
    private CheckBox showPasswordCheckBox;

    @FXML
    private Button loginButton;

    @FXML
    public void initialize() {
        // Sync the visible password field with the actual password field
        visiblePasswordField.managedProperty().bind(showPasswordCheckBox.selectedProperty());
        visiblePasswordField.visibleProperty().bind(showPasswordCheckBox.selectedProperty());
        passwordField.visibleProperty().bind(showPasswordCheckBox.selectedProperty().not());

        // Mirror text between PasswordField and TextField
        visiblePasswordField.textProperty().bindBidirectional(passwordField.textProperty());

        // Login button action (basic example)
        loginButton.setOnAction(event -> handleLogin());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("admin") && password.equals("1234")) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }


    public void actionTestBtn(ActionEvent actionEvent) {

//        List<User> listOfUsers = userDAO.getAllUsers();
//        for(User user : listOfUsers) {
//            System.out.println(user);
//        }

//        List<User> listEventCords = userDAO.getAllEventCoordinators();
//        for (User user : listEventCords) {
//            System.out.println(user);
//        }

          EventManagement eventMan = new EventManagement();
//        eventMan.createEvent("Dumb City","Yesterday","2 days ago","8 days later", "Come to event plz <3", 5000, "follow me", "Delete Testing");
          eventMan.deleteEvent("Delete Testing");

    }
}
