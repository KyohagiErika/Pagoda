package com.view;

import com.model.GuestManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class SeeGuestInformation extends ConsolePage {
    public SeeGuestInformation(PagodaApp application) {
        super(application, "See Guest Information");
    }

    public void display(GuestManager guest) {
        application.clrscr();
        super.display();
        HashMap<String, String> currentUser = (HashMap<String, String>) application.getSession().get("currentUser");
        HashMap<String, String> records = null;
        try {
            records = guest.fromDatabase(new HashMap<String, String>() {{
                put("username", currentUser.get("username"));
                put("pass", currentUser.get("pass"));
            }}).get(0);
            System.out.println();
            System.out.println("Last Name: " + records.get("lastName"));
            System.out.println("First Name: " + records.get("firstName"));
            System.out.println("Phone Number: " + records.get("phoneNumber"));
            System.out.println("Email: " + records.get("email"));
            System.out.println("National ID: " + records.get("cccd"));
            System.out.println("Register Day: " + DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.parse(records.get("regDay"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
        }
        application.pause();
    }
}
