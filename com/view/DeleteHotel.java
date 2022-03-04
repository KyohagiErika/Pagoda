package com.view;

import com.model.HotelManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.util.HashMap;

public class DeleteHotel extends ConsolePage {
    public DeleteHotel(PagodaApp application) {
        super(application, "Delete Hotel");
    }

    @Override
    public void display(HotelManager hotel) {
        application.clrscr();
        super.display();
        ConsoleInput textReader = new ConsoleInput();

        HashMap<String, String> currentUser= (HashMap<String, String>)application.getSession().get("currentUser");
        try {
            hotel.deleteRecord(new HashMap<String, String>(){{
                put("hotelID",currentUser.get("hotelID"));
            }});
            System.out.println();
            System.out.println("Delete Guest successfully!");
        } catch (SQLException e) {
            System.out.println();
            System.out.println("Some error occurred!");
            application.writeLog(e);
        }
        application.pause();
    }
}
