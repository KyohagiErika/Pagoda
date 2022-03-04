package com.view;

import com.model.HotelManager;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.util.HashMap;


public class EditHotelInformation extends ConsolePage {
    public EditHotelInformation(PagodaApp application) {
        super(application, "Edit Hotel Information here");
    }

    @Override
    public void display(HotelManager hotel) {
        application.clrscr();
        super.display();
        ConsoleInput textReader = new ConsoleInput();
        HashMap<String, String> currentUser = (HashMap<String, String>) application.getSession().get("currentUser");
        try {
            hotel.editRecord(currentUser,new HashMap<String, String>(){{
                put("lastName",textReader.input("Last Name"));
                put("firstName",textReader.input("First Name"));
                put("phoneNumber",textReader.input("Phone Number"));
                put("email",textReader.input("Email"));
                put("cccd",textReader.input("National ID"));
                put("name",textReader.input("Hotel Name"));
                put("description",textReader.input("Hotel Description"));
                put("starRank",textReader.input("Star Rank"));
                put("image", textReader.input("Hotel Image"));
                put("location",textReader.input("Hotel Location"));
            }});
            System.out.println();
            System.out.println("Edit Hotel Information successfully!");

        } catch (SQLException e) {
            System.out.println();
            System.out.println("Some error occurred!");
            application.writeLog(e);
        }
        application.pause();
    }

}
