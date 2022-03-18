package com.view;

import com.model.BookRoomManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class SeeBookRoomListOfRoom extends ConsolePage {
    /**
     * Create a console page.
     *
     * @param application The application to which the page belongs.
     */
    public SeeBookRoomListOfRoom(PagodaApp application) {
        super(application, "BookRoom List Of Room");
    }
    public void display(BookRoomManager room, String hotelID, String roomID) {
        application.clrscr();
        super.display();
        try {
            ArrayList<HashMap<String, String>> listBookRooms = room.seeBookRoomListOfRoom(new HashMap<String, String>() {{
                put("hotelID", hotelID);
                put("roomID", roomID);
            }});
            if(listBookRooms.size()!=0) {
                System.out.printf("%12s | %20s | %20s","BookRoom ID","Check In","Check Out");
                System.out.println();
                for(HashMap<String, String> item : listBookRooms) {
                    System.out.printf("%12s | %20s | %20s",item.get("bookroomID"),DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.parse(item.get("checkIn"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))),
                            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.parse(item.get("checkOut"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                    System.out.println();
                }

                System.out.println();
                application.pause();
            } else {
                System.out.println("No one has booked this room yet!");
                application.pause();
                application.clrscr();
            }
        } catch (SQLException e) {
            application.writeLog(e);
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
            application.pause();
        }
    }
}
