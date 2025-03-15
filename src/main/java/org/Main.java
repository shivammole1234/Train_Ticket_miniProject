package org;
import entities.Train;
import entities.User;
import servises.UserBookingService;
import utils.UserServiceUtils;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        System.out.println("Running train booking system ");
        Scanner scanner = new Scanner(System.in);

        int option = 0;

        UserBookingService userBookingService;
        try {
            userBookingService = new UserBookingService();
        } catch (IOException e) {
            System.out.println("there is something wrong");
            throw new RuntimeException(e);
        }

        while (option != 7) {
            System.out.println("choose option");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Fetch Booking");
            System.out.println("4. Cancel Booking");
            System.out.println("5 book seat");
            System.out.println("6. search train");
            System.out.println("7 exit ");
            System.out.println("enter your choice");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("enter username");
                    String userNameToLogin = scanner.next();
                    System.out.println("enter password");
                    String passwordToLogin = scanner.next();
                    User userToLogin=new User(userNameToLogin,passwordToLogin,UserServiceUtils.hashPassword(passwordToLogin),new ArrayList<>(),UUID.randomUUID().toString());

                    try{
                        userBookingService=new UserBookingService(userToLogin);
                    }catch (IOException ex){
                        System.out.println("error in login user first option ");
                        return;
                    }
                    break;
                case 2:
                    System.out.println("enter username to signup");
                    String userNameToSignUp = scanner.next();
                    System.out.println("enter password to signup");
                    String passwordToSingUp = scanner.next();
                    User userToSignUp = new User(userNameToSignUp, passwordToSingUp,
                            UserServiceUtils.hashPassword(passwordToSingUp),
                            new ArrayList<>(), UUID.randomUUID().toString());
                    boolean isSignUp = userBookingService.signUp(userToSignUp);
                    if (isSignUp)
                        System.out.println("Sign Up Successful");
                    else
                        System.out.println("Sign Up Failed");
                    break;
                case 3:
                    userBookingService.fetchBooking();
                    break;
                case 4:
                    userBookingService.cancleBooking(null);
                    break;
                case 5:
                    System.out.println("type ur soruce destination");
                    String source = scanner.next();
                    System.out.println("type your destinatoin station");
                    String dest = scanner.next();
                    List<Train> trains = userBookingService.getTrain(source, dest);
                    for(Train T : trains){
                        System.out.println("Tain ID:- " +T.getTrainId());
                        for(Map.Entry<String,String> entry : T.getStationTimes().entrySet()){
                            System.out.println("serachin for train");
                            System.out.println("Station :- "+entry.getKey()+" and station time is :- "+entry.getValue());
                        }
                    }
                    break;

                case 6:
                    break;

                default:
                    break;

            }

        }

    }
}
