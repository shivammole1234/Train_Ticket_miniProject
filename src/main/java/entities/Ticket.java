package entities;

public class Ticket  {
    private String ticketId;
    private String userId;
    private String source;
    private String destination;
    private String dateOfTravel;
    private Train train;
    private String ticketInfo;
    public Ticket(){

    }
    public Ticket(String ticketId, String userId, String destination, String source, String dateOfTravel, Train train) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.destination = destination;
        this.source = source;
        this.dateOfTravel = dateOfTravel;
        this.train = train;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getSource() {
        return source;
    }

    public String getUserId() {
        return userId;
    }

    public String getDestination() {
        return destination;
    }

    public String getDateOfTravel() {
        return dateOfTravel;
    }

    public Train getTrain() {
        return train;
    }

    public String getTicketInfo(){
        return String
                .format("Ticket ID : %s belongs to USer %s from %s to %s on %s ",ticketId,userId,source,destination,dateOfTravel);
    }
}
