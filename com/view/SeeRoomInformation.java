package com.view;

import com.model.RoomManager;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SeeRoomInformation extends ConsolePage {
    public SeeRoomInformation(PagodaApp application) {
        super(application, "See Room Information");
    }

    @Override
    public void display(RoomManager room) {
        application.clrscr();
        super.display();
        HashMap<String, String> currentUser = (HashMap<String, String>)application.getSession().get("currentUser");
        try {
            ArrayList<HashMap<String, String>> viewRooms = room.viewRoom(new HashMap<String, String>() {{
                put("hotelID", currentUser.get("hotelID"));
            }});
            System.out.println();
            System.out.printf("%10s | %15s | %10s | %15s | %10s | %25s","Room ID","Room Name","Room Size","Price Per Night","Capacity","Image");
            System.out.println();
            for(HashMap<String, String> item : viewRooms) {
                System.out.printf("%10s | %15s | %10s | %15s | %10s | %25s",item.get("roomID"),item.get("name"),item.get("roomSize"),
                        item.get("pricePerNight"),item.get("capacity"),item.get("image"));
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Some error occurred!");
            application.writeLog(e);
        }
        application.pause();
    }
}
