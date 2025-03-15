package servises;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Ticket;
import entities.Train;
import entities.User;
import utils.UserServiceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserBookingService {

    private User user;
    private Ticket tickets;

    private List<User> userList;
    private  List<Ticket> ticketList;

    // object mapper
    private  ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH="C:\\Users\\shiv\\java_workspace\\LovePrit_SpringBoot\\IRCTC_TicketBooking\\src\\main\\localDB\\users.json";
    public UserBookingService(User user) throws IOException {
        this.user=user;
         loadUser();
    }

    public UserBookingService() throws IOException {
        loadUser();
    }

    public List<User> loadUser() throws IOException {
        File users=new File(USERS_PATH);
        // deserialize
        userList=objectMapper.readValue(users,new TypeReference<List<User>>() {});
    return userList;
    }


    private void saveUserListToFile() throws IOException{
        File usersFile=new File(USERS_PATH);
        objectMapper.writeValue(usersFile,userList);
    }

    public boolean loginUser(){
        Optional<User> foundUser=userList.stream().filter(user1 ->{
            return user1.getUserName().equalsIgnoreCase(user.getUserName()) && UserServiceUtils
                    .checkPassword(user.getPassword(),user1.getHashPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return true;
        }catch (IOException ex)
        {
            return false;
        }
    }

    public void fetchBooking(){
        user.printTickets();
    }

    public boolean cancleBooking(String ticketId){
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the ticket ID to delete:- ");
        ticketId= sc.next();

        if(ticketId == null || ticketId.isEmpty()){
            System.out.println("Ticket ID cannot be null or empty");
            return false;
        }
        String finalTicketId=ticketId;
        boolean isRemoved=user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equalsIgnoreCase(finalTicketId));

        if(isRemoved)
        {
            System.out.println("ticket with Ticket ID "+ticketId+" has been cancle");
            return true;
        }else{
            System.out.println("No Ticket found with ID "+ticketId);
            return  false;
        }
    }

    public List<Train>  getTrain(String source,String destination) throws IOException {

        TrainService trainService=new TrainService();
        return trainService.searchTrain(source,destination);

    }


}