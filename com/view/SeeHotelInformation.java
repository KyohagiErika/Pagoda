package com.view;

import com.model.HotelManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class SeeHotelInformation extends ConsolePage {

    /**
     * Create a console page.
     *
     * @param application The application to which the page belongs.
     */
    public SeeHotelInformation(PagodaApp application) {
        super(application, "See Hotel Information");
    }

    public void display(HotelManager hotel) {
        application.clrscr();
        super.display();
        HashMap<String, String> currentUser = (HashMap<String, String>) application.getSession().get("currentUser");

        HashMap<String, String> records = null;
        try {
            records = hotel.fromDatabase(new HashMap<String, String>() {{
                put("username",currentUser.get("username"));
                put("pass",currentUser.get("pass"));
            }}).get(0);

            System.out.println();
            System.out.println("Last Name: "+records.get("lastName"));
            System.out.println("First Name: "+records.get("firstName"));
            System.out.println("Phone Number: "+records.get("phoneNumber"));
            System.out.println("Email: "+records.get("email"));
            System.out.println("National ID: "+records.get("cccd"));
            System.out.println();
            System.out.println("Hotel ID: "+records.get("hotelID"));
            System.out.println("Hotel Name: "+records.get("name"));
            System.out.println("Hotel Description: "+records.get("description"));
            System.out.println("Star Rank: "+records.get("starRank"));
            System.out.println("Hotel Image: "+records.get("image"));
            System.out.println("Hotel Location: "+records.get("location"));
            System.out.println("Register Day: "+ DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.parse(records.get("regDay"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
            System.out.println();
        } catch (SQLException e) {
            application.writeLog(e);
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
        }
        application.pause();
    }
}
