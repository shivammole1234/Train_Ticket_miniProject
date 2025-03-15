package entities;

import java.util.List;

public class User {

    private String userName;
    private String password;
    private String hashPassword;
    private List<Ticket> ticketsBooked;
    private String userId;

    public User(){

    }

    public User(String userName, String password, String hashPassword, List<Ticket> ticketsBooked, String userId) {
        this.userName = userName;
        this.password = password;
        this.hashPassword = hashPassword;
        this.ticketsBooked = ticketsBooked;
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void printTickets(){
        System.out.println("printing the user tcket");
        for(int i=0;i<ticketsBooked.size();i++)
            System.out.println(ticketsBooked.get(i).getTicketInfo());
    }
}
