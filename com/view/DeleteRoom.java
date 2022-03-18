package com.view;

import com.model.RoomManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static com.viewmodel.application.PagodaAppMenu.PAGODA_MENU_DEFAULT_INPUT_MESSAGE;

public class DeleteRoom  extends ConsolePage {
    public DeleteRoom(PagodaApp application) {
        super(application, "Delete Room");
    }

    public void display(RoomManager room, String hotelID) {
        super.display();
        ConsoleInput textReader = new ConsoleInput();
        try {
            ArrayList<HashMap<String, String>> viewRoomList = room.viewRoom(new HashMap<String, String>() {{
                put("hotelID", hotelID);
            }});
            if(viewRoomList.size()!=0) {
                System.out.println();
                System.out.println("Enter the room you want to delete: ");
                Option[] ops = new Option[viewRoomList.size()+1];
                for(int i=0; i< viewRoomList.size();i++) {
                    HashMap<String, String> viewRoom = viewRoomList.get(i);
                    ops[i] = new Option("Room ID: "+viewRoom.get("roomID")+", Room Name: "+viewRoom.get("name"),(obj_) -> {
                        application.clrscr();
                        System.out.println("Deleting Your Room : .....");
                        System.out.println("Are you sure ?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        String inputMsg = "Input your choice";
                        Scanner reader = new Scanner(System.in);
                        int optionIndex;
                        do {
                            optionIndex = 0;
                            if (inputMsg.compareTo("") != 0) {
                                System.out.print(inputMsg + ": ");
                            }
                            if (reader.hasNextInt()) {
                                optionIndex = reader.nextInt();
                            }
                            reader.nextLine();
                        } while (!(optionIndex > 0 && optionIndex <= 2));
                        if(optionIndex == 1) {
                            try {
                                room.deleteRecord(new HashMap<>() {{
                                    put("roomID", viewRoom.get("roomID"));
                                    put("hotelID", hotelID);
                                }});
                            } catch (SQLException e) {
                                application.writeLog(e);
                                System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                                application.pause();
                            }
                            System.out.println();
                            System.out.println("Delete Room successfully!");
                            application.pause();
                        }
                        return null;
                    });
                }
                ops[ops.length-1] = new Option("Back",(obj_)-> {
                    application.clrscr();
                    RoomInformation roomInformation = new RoomInformation((PagodaApp) application);
                    roomInformation.display();
                    return null;
                });
                application.clrscr();
                PagodaAppMenu roomMenu = new PagodaAppMenu((PagodaApp) application,"Room List",ops);
                roomMenu.display();
                roomMenu.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);

            } else {
                System.out.println("This hotel has no room!");
                application.pause();
            }
        } catch (SQLException e) {
            application.writeLog(e);
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
            application.pause();
        }
    }
}
