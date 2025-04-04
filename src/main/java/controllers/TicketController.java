package controllers;

import be.Ticket;
import bll.TicketManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class TicketController {
    private TicketManager ticketManager;

    @FXML
    private Label lblTicketId; // Declarar el Label para mostrar el ID

    @FXML
    private ImageView imgBarcode; // Declarar el ImageView para el código de barras

    @FXML
    public void initialize() {
        // Add logic if is needed
    }

    // Ticket manager
    public void setTicketManager(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
    }

    @FXML
    public void generateTicket() {
        if (ticketManager == null) {
            System.out.println("Error: TicketManager no founded.");
            return;
        }

        int userId = 1;
        int eventId = 5;
        String ticketType = "50% off drinks";

        Ticket ticket = ticketManager.generateTicket(userId, eventId, ticketType);
        System.out.println("Ticket generated: " + ticket);

//        lblTicketId.setText("Ticket ID: " + ticket.getId());

        //TODO: change ticket.getBarcodeImage() to getBarcodeImageURL
        // getting code bar
        try {
            File barcodeFile = new File("resources/Barcode/" + ticket.getBarcodeImage());
            if (barcodeFile.exists()) {
                Image barcodeImage = new Image(barcodeFile.toURI().toString());
                imgBarcode.setImage(barcodeImage);
            } else {
                System.out.println("Error: Barcode not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void showTicketWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TicketView.fxml"));
            Parent root = loader.load();

            TicketController controller = loader.getController();
            controller.setTicketManager(ticketManager);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Ticket Window");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar la ventana de Ticket.");
        }
    }
}