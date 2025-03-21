package controllers;

import be.User;
import bll.UserManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class AdminMainController {

    @FXML
    private ListView<User> eventCoordsListView;

    private final UserManagement userManagement = new UserManagement();

    @FXML
    private void initialize() {
        loadEventCoordinators();
    }

    // we better have realistic event coordinators' names before presantation and we should be able to
    //open the admin page after login as a admin
    private void loadEventCoordinators() {
        List<User> coordinators = userManagement.getAllEventCoordinators();
        ObservableList<User> observableList = FXCollections.observableArrayList(coordinators);
        eventCoordsListView.setItems(observableList);
    }


}
