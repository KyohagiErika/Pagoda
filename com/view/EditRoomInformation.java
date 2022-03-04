package com.view;

import com.model.RoomManager;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.util.HashMap;

public class EditRoomInformation extends ConsolePage {
    public EditRoomInformation(PagodaApp application) {
        super(application, "Edit Room Information here");
    }

    @Override
    public void display(RoomManager room) {
        application.clrscr();
        super.display();
        ConsoleInput textReader = new ConsoleInput();
        HashMap<String, String> currentUser = (HashMap<String, String>)application.getSession().get("currentUser");
        try {
            room.editRecord(new HashMap<>() {{
                put("roomID",textReader.input("Room ID"));
                put("HotelID",currentUser.get("hotelID"));
            }}, new HashMap<>() {{
                put("name",textReader.input("Room Name"));
                put("roomSize",textReader.input("Room Size"));
                put("pricePerNight",textReader.input("Price Per Night"));
                put("capacity",textReader.input("Capacity"));
                put("image",textReader.input("Image"));
            }});
            System.out.println("Edit Room Information successfully!");
        } catch (SQLException e) {
            System.out.println("Some error occurred!");
            application.writeLog(e);
        }
        application.pause();
    }
}
