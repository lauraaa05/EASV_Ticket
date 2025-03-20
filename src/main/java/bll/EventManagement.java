package bll;

import dal.EventDAO;
import be.Event;

public class EventManagement {

    private final EventDAO eventDAO = new EventDAO();

    public void createEvent(String location, String date, String startTime, String endTime, String note, int price, String location_Guidance, String eventName) {

        Event event = new Event(location, date, startTime, endTime, note, price, location_Guidance, eventName);
        eventDAO.createEvent(event);
    }

    public void deleteEvent(String eventName) {
        eventDAO.deleteEvent(eventName);
    }
}
