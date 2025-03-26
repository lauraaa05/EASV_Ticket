package bll;
import be.Ticket;
import dal.TicketDAO;

import java.sql.SQLException;
import java.util.UUID;

public class TicketManager {
    private TicketDAO ticketDAO; // Corrected

    public TicketManager(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    //TODO: Check BarCodeGenetator.java, is already generating barcode and image
    // Change barcode = BarCodeGenerator.generateBarcode("123456789"); ?
    public Ticket generateTicket(String userId) {
        String uniqueId = UUID.randomUUID().toString();
        String barcode = BarCodeGenerator.generateBarcode("123456789");


        Ticket ticket = new Ticket(userId,barcode);
        try {
            ticketDAO.saveTicket(ticket); //Class atributte
        } catch (SQLException e) {
            e.printStackTrace(); // Manage errors
        }
        return ticket;
    }
}
