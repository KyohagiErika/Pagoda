package com.view;

import com.model.BookRoomManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerInformation extends ConsolePage {
    public CustomerInformation(PagodaApp application) {
        super(application, "Customer Information");
    }

    public void display(BookRoomManager bookRoom) {
        application.clrscr();
        super.display();
        HashMap<String, String> currentUser = (HashMap<String, String>)application.getSession().get("currentUser");
        try {
            ArrayList<HashMap<String, String>> viewCustomers = bookRoom.seeCustomerInformation(new HashMap<String, String>() {{
                put("hotelID", currentUser.get("hotelID"));
            }});
            System.out.println();
            System.out.printf("%10s | %20s | %20s | %20s | %20s |  %15s | %15s | %15s | %20s | %15s","Room ID","Book Day",
                    "Check In","Check Out","Total Money","Last Name","First Name","Phone Number","Email","National ID");
            System.out.println();
            for(HashMap<String, String> item : viewCustomers) {
                Duration duration = Duration.between(LocalDateTime.parse(item.get("checkIn"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        LocalDateTime.parse(item.get("checkOut"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                System.out.printf("%10s | %20s | %20s | %20s | %20d | %15s | %15s | %15s | %20s | %15s",item.get("roomID"),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.parse(item.get("bookDay"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.parse(item.get("checkIn"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.parse(item.get("checkOut"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))),
                        duration.toHours()*bookRoom.seePricePerNightOfRoom(currentUser.get("hotelID"),item.get("roomID"))/24,item.get("lastName"),
                        item.get("firstName"),item.get("phoneNumber"),item.get("email"),item.get("cccd"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println();
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
        application.pause();
    }
}
