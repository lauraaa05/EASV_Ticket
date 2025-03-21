package controllers;

import be.Event;
import bll.EventManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EventMainController {

    @FXML
    private ListView eventsListView;

    private final EventManagement eventManagement = new EventManagement();


    @FXML
    public void initialize() {
        loadEvents();
    }

    public void loadEvents() {
        List<Event> events = eventManagement.getAllEvents();

        List<String> eventNames = events.stream()
                .map(Event::getEventName)
                .collect(Collectors.toList());

        ObservableList<String> observableList = FXCollections.observableArrayList(eventNames);
        eventsListView.setItems(observableList);
    }




}
