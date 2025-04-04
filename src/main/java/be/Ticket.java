package be;

import java.util.Random;
import java.util.UUID;

public class Ticket {
    private int customerId; // User to generated ticket
    private int eventId;
    private String ticketType;
    private int ticketId;
    private byte[] barcodeImage;// BarCode format 128
    private int barcodeId;
    private String barcodeString;

    // Constructor
    public Ticket(int userId, int eventId, String ticketType, byte[] barcodeImage, int barcodeId, String barcodeString) {
        this.customerId = userId;
        this.eventId = eventId;
        this.ticketType = ticketType;
        this.barcodeImage = barcodeImage;
        this.barcodeId = barcodeId;
        this.barcodeString = barcodeString;
    }

    public Ticket() {}

    // One unique Code Bar with Random number
    private String generateUniqueId() {
        String uuidPart = UUID.randomUUID().toString().substring(0, 8); // 8 typos UUID
        int randomPart = new Random().nextInt(10000); // Random
        return uuidPart + "-" + randomPart;
    }

    //TODO
    // BarCode format E128
    private String generateBarcode() {
        Random random = new Random();
        StringBuilder barcodeBuilder = new StringBuilder();

        // Code 128 Alphanumeric
        for (int i = 0; i < 12; i++) { // Code 128 used 12 numbers
            int digit = random.nextInt(10); // Number from 0 to 9
            barcodeBuilder.append(digit);
        }
        return barcodeBuilder.toString();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", customerId=" + customerId +
                ", eventId=" + eventId +
                ", ticketType='" + ticketType + '\'' +
                ", barcodeId='" + barcodeId + '\'' +
                ", barcodeString='" + barcodeString + '\'' +
                '}';
    }

    // Getters
    public int getCustomerId() {
        return customerId;
    }

    public int getEventId() {
        return eventId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public int getTicketId() {
        return ticketId;
    }

    public byte[] getBarcodeImage() {
        return barcodeImage; // Valid format CodeBar
    }

    public int getBarcodeId() {
        return barcodeId;
    }

    public String getBarcodeString() {
        return barcodeString;
    }

    // Setters
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setBarcodeImage(byte[] barcodeImage) {
        this.barcodeImage = barcodeImage;
    }

    public void setBarcodeId(int barcodeId) {
        this.barcodeId = barcodeId;
    }

    public void setBarcodeString(String barcodeString) {
        this.barcodeString = barcodeString;
    }
}