package be;

public class TicketType {
    private String name;

    public TicketType(String ticketType) {
        this.name = ticketType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
