package dal;

import be.Barcode;
import java.sql.*;

public class BarcodeDAO {

    private final Connection connection;
    private DBAccess dbAccess;

    public BarcodeDAO(Connection connection) {
        this.connection = connection;
    }

    public int saveBarcode(Barcode barcode) throws SQLException {
        if (connection == null) {
            throw new SQLException("Connection is NULL");
        }

        // Print out the size of the byte array before saving it to the database
        if (barcode.getBarcodeImage() == null) {
            System.out.println("Error: Barcode data is null.");
        } else {
            System.out.println("Barcode data size: " + barcode.getBarcodeImage().length + " bytes");
        }

        // Update the column name to 'Barcode_Number'
        String sql = "INSERT INTO Barcode (BarcodeImage, BarcodeString) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setBytes(1, barcode.getBarcodeImage()); // Set the VARBINARY data
            stmt.setString(2, barcode.getBarcodeString());
            stmt.executeUpdate();

            // Retrieve generated keys (BarcodeId)
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    int generatedId = keys.getInt(1);
                    barcode.setBarcodeId(generatedId); // Set the ID in the Barcode object
                    return generatedId;
                }
            }
        }
        return -1; // Return -1 if the insertion failed
    }

    public int saveSpecialTicketBarcode(Barcode barcode) throws SQLException {
        try (Connection conn = dbAccess.DBConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO Barcode (BarcodeImage, BarcodeString) VALUES (?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            if (barcode.getBarcodeImage() == null) {
                System.out.println("Error: Barcode data is null.");
            } else {
                System.out.println("Barcode data size: " + barcode.getBarcodeImage().length + " bytes");
            }

            stmt.setBytes(1, barcode.getBarcodeImage());
            stmt.setString(2, barcode.getBarcodeString());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    int generatedId = keys.getInt(1);
                    barcode.setBarcodeId(generatedId);
                    return generatedId;
                }
            }
        }
        return -1;
    }

    public Barcode getBarcodeById(int barcodeId) throws SQLException {
        if (connection == null) {
            throw new SQLException("Connection is NULL");
        }

        String sql = "SELECT BarcodeId, BarcodeImage, BarcodeString FROM Barcode WHERE BarcodeId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, barcodeId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("BarcodeId");
                    byte[] image = rs.getBytes("BarcodeImage");
                    String string = rs.getString("BarcodeString");

                    // Return the Barcode object with the retrieved data
                    return new Barcode(id, image, string);
                }
            }
        }
        return null; // Return null if no barcode found
    }
}