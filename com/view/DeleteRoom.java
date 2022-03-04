package com.view;

import com.model.RoomManager;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.util.HashMap;

public class DeleteRoom  extends ConsolePage {
    public DeleteRoom(PagodaApp application) {
        super(application, "Delete Room");
    }

    @Override
    public void display(RoomManager room) {
        application.clrscr();
        super.display();
        ConsoleInput textReader = new ConsoleInput();
        HashMap<String, String> currentUser = (HashMap<String, String>)application.getSession().get("currentUser");
        try {
            room.deleteRecord(new HashMap<>() {{
                put("roomID",textReader.input("Room ID"));
                put("hotelID",currentUser.get("hotelID"));
            }});
            System.out.println("Delete Room successfully!");
        } catch (SQLException e) {
            System.out.println("Some error occurred!");
            application.writeLog(e);
        }
        application.pause();
    }
}
