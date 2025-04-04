package dk.easv;
import dal.BarcodeDAO;
import be.Barcode;
import dal.DBAccess; // Import your DBAccess class

import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;

public class BarcodeTest {

    public static void main(String[] args) {
        DBAccess dbAccess = new DBAccess(); // Create an instance of your Connection Manager

        try (Connection connection = dbAccess.DBConnection()) { // Get a connection to your database
            if (connection != null && !connection.isClosed()) {
                System.out.println("Database connection established successfully!");

                // Create an instance of BarcodeDAO using the active connection
                BarcodeDAO barcodeDAO = new BarcodeDAO(connection);

                // Example - Saving a barcode to the database
                File barcodeImage = new File("resources/Barcode/42549ADD4011.png"); // Replace with your image path
                byte[] barcodeBytes = Files.readAllBytes(barcodeImage.toPath());

                // Create a new Barcode object to save
                Barcode newBarcode = new Barcode(barcodeBytes);
                int generatedId = barcodeDAO.saveBarcode(newBarcode);

                System.out.println("Barcode saved with ID: " + generatedId);

                // Example - Retrieving a barcode by ID
                Barcode retrievedBarcode = barcodeDAO.getBarcodeById(generatedId);
                if (retrievedBarcode != null) {
                    System.out.println("Barcode retrieved with ID: " + retrievedBarcode.getBarcodeId());
                    System.out.println("Barcode data size: " + retrievedBarcode.getBarcodeImage().length + " bytes");
                } else {
                    System.out.println("Barcode not found.");
                }

            } else {
                System.out.println("Failed to establish database connection.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
