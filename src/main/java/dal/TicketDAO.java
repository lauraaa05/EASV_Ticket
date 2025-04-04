package dal;

import be.Event;
import be.Ticket;

import java.sql.*;

public class TicketDAO {
    private Event event;
    private Connection connection;

    // Constructor
    public TicketDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveTicket(Ticket ticket) throws SQLException {
        if (connection == null) {
            throw new SQLException("Cant get connection: conexión is NULL");
        }

        String sql = "INSERT INTO Ticket (BarcodeId, CustomerId, TicketType, EventId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, ticket.getBarcodeId());
            stmt.setInt(2, ticket.getCustomerId());
            stmt.setString(3, ticket.getTicketType());
            stmt.setInt(4, ticket.getEventId());
            stmt.executeUpdate();

            //Gets the ticket id so we can print it on the ticket
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    ticket.setTicketId(keys.getInt(1));
                }
            }
        }
    }
}