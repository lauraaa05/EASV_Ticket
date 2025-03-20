package dal;

import be.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventDAO {

    private final DBAccess dbAccess = new DBAccess();

    public void createEvent(Event event) {
        String sql = "INSERT INTO Event (Location, Date, StartTime, EndTime, Note, Price, Location_Guidance, EventName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbAccess.DBConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getLocation());
            stmt.setString(2, event.getDate());
            stmt.setString(3, event.getStartTime());
            stmt.setString(4, event.getEndTime());
            stmt.setString(5,event.getNote());
            stmt.setInt(6,event.getPrice());
            stmt.setString(7,event.getLocation_Guidance());
            stmt.setString(8,event.getEventName());

            stmt.executeUpdate();
            System.out.println("Event added successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEvent(String eventName){

        String sql = "DELETE FROM Event WHERE EventName = ?";

        try(Connection conn = dbAccess.DBConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,eventName);
            stmt.executeUpdate();
            System.out.println("Event deleted successfully");
        }   catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
