package com.view;

import com.model.BookRoom;
import com.model.GuestManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.processor.enumeration.ConsoleInputMode;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SeeBooking extends ConsolePage {
    public SeeBooking(PagodaApp application) {
        super(application, "See Your Booking Here");
    }

    @Override
    public void display(BookRoom bookRoom) {
        application.clrscr();
        super.display();
        ConsoleInput textReader = new ConsoleInput();
        ArrayList<HashMap<String, String>> records = null;
        HashMap<String, String> currentUser = (HashMap<String, String>)application.getSession().get("currentUser");

        try {
            records= bookRoom.fromDatabase(new HashMap<String,String>(){{
                put("guest_username", currentUser.get("username"));
                put("room_room_Id", textReader.input("Room ID"));
                put("hotel_hotel_id", textReader.input("Hotel ID"));
                put("book_day", textReader.input("Book Day"));
            }});

            if(records.size()>0){
                application.getSession().put("BookingInfo", records.get(3));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
