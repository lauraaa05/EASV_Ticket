package dal;

import be.TicketType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketTypeDAO {
    private Connection conn;

    public TicketTypeDAO(Connection conn) {
        this.conn = conn;
    }

    public void createTicketType(TicketType ticketType) throws SQLException {
        String sql = "INSERT INTO TicketType (Name) VALUES (?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, ticketType.getName());
        }
    }

    public List<TicketType> getAllTicketTypes() throws SQLException {
        List<TicketType> ticketTypes = new ArrayList<TicketType>();
        String sql = "SELECT * FROM TicketType";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String ticketTypeName = rs.getString("Name");

                TicketType ticketType = new TicketType(ticketTypeName);
                ticketTypes.add(ticketType);
            }
        }
        return ticketTypes;
    }

    public void updateTicketType(TicketType ticketType) throws SQLException {
        String sql = "UPDATE TicketType SET Name = ? WHERE Name = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, ticketType.getName());
            statement.executeUpdate();
        }
    }

    public void deleteTicketType(TicketType ticketType) throws SQLException {
        String sql = "DELETE FROM TicketType WHERE Name = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, ticketType.getName());
            statement.executeUpdate();
        }
    }
}