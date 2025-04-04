package dk.easv;

import be.SpecialTicket;
import bll.SpecialTicketManager;
import dal.BarcodeDAO;
import dal.DBAccess;
import dal.SpecialTicketDAO;

import java.sql.Connection;
import java.util.Optional;

public class SpecialTicketTest {

        public static void main(String[] args) {
            try {
                DBAccess db = new DBAccess();
                Connection conn = db.DBConnection();
                SpecialTicketDAO specialTicketDAO = new SpecialTicketDAO(conn);
                BarcodeDAO barcodeDAO = new BarcodeDAO(conn);

                SpecialTicketManager specialTicketManager = new SpecialTicketManager(specialTicketDAO, barcodeDAO);

                // Test case 1: Providing an EventId
                SpecialTicket specialTicketWithEvent = specialTicketManager.generateSpecialTicket(Optional.of(2), "Free beer");
                if (specialTicketWithEvent != null) {
                    System.out.println("Special ticket with EventId generated successfully:");
                    System.out.println(specialTicketWithEvent);
                } else {
                    System.out.println("Special ticket with EventId could not be generated.");
                }

                // Test case 2: Without providing an EventId (NULL EventId)
                SpecialTicket specialTicketWithoutEvent = specialTicketManager.generateSpecialTicket(Optional.empty(), "No Event Ticket");
                if (specialTicketWithoutEvent != null) {
                    System.out.println("Special ticket without EventId generated successfully:");
                    System.out.println(specialTicketWithoutEvent);
                } else {
                    System.out.println("Special ticket without EventId could not be generated.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }