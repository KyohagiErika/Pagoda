package com.view;

import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.util.HashMap;

public class BookRoom extends ConsolePage {

    public BookRoom(PagodaApp application) {
        super(application, "Book Room");
    }

    @Override
    public void display(com.model.BookRoom bookRoom) {
        application.clrscr();
        super.display();
        ConsoleInput textReader = new ConsoleInput();
        HashMap<String, String> currentUser = (HashMap<String, String>) application.getSession().get("currentUser");
        try {
            bookRoom.toDatabase(new HashMap<String,String>(){{
                put("bookRoomID",textReader.input(" Book Room ID"));
               // put("guestUsername",currentUser.get("username"));
                put("roomID",textReader.input("Room ID"));
               // put("hotelID",currentUser.get("hotelID"));
                put("bookDay",textReader.input(" Book Day"));
                put("checkIn",textReader.input("Checkin"));
                put("checkOut",textReader.input(" Checkout"));
                put("paymentMethod",textReader.input(" Payment Method"));
                put("noteOfGuests",textReader.input(" Note"));
            }});

            System.out.println("Book Room Successfully !");

        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace();
            System.out.println();
            System.out.println("Some error occurred!");
            application.writeLog(e);
        }
        application.pause();
    }
}
