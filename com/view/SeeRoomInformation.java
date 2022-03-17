package com.view;

import com.model.BookRoomManager;
import com.model.RoomManager;
import com.model.SubscriptionManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsolePage;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.viewmodel.application.PagodaAppMenu.PAGODA_MENU_DEFAULT_INPUT_MESSAGE;

public class SeeRoomInformation extends ConsolePage {
    public SeeRoomInformation(PagodaApp application) {
        super(application, "See Room Information");
    }

    public void display(RoomManager room, String hotelID) {
        application.clrscr();
        super.display();
//        HashMap<String, String> currentUser = (HashMap<String, String>)application.getSession().get("currentUser");
        try {
            ArrayList<HashMap<String, String>> viewRoomList = room.viewRoom(new HashMap<String, String>() {{
                put("hotelID", hotelID);
            }});
            if(viewRoomList.size()!=0) {
                System.out.println();
                Option[] ops = new Option[viewRoomList.size()+1];
                for(int i=0; i< viewRoomList.size();i++) {
                    HashMap<String, String> viewRoom = viewRoomList.get(i);
                    ops[i] = new Option("Room ID: "+viewRoom.get("roomID")+", Room Name: "+viewRoom.get("name"),(obj_) -> {
                        application.clrscr();
                        System.out.println("Room information:");
                        System.out.println();
                        System.out.println("Room ID: "+viewRoom.get("roomID"));
                        System.out.println("Room Name: "+viewRoom.get("name"));
                        System.out.println("Room Size: "+viewRoom.get("roomSize"));
                        System.out.println("Price Per Night: "+viewRoom.get("pricePerNight"));
                        System.out.println("Room Capacity: "+viewRoom.get("capacity"));
                        System.out.println("Room Image: "+viewRoom.get("image"));

                        application.pause();
                        System.out.println();
                        new SeeBookRoomListOfRoom((PagodaApp) application).display(new BookRoomManager(((PagodaApp) application).getConnection()),hotelID,viewRoom.get("roomID"));
                        new BookRoom((PagodaApp) application).display(new SubscriptionManager(((PagodaApp) application).getConnection()), new BookRoomManager(((PagodaApp) application).getConnection()),hotelID,viewRoom.get("roomID"));

                        return null;
                    });
                }
                ops[ops.length-1] = new Option("Back",(obj_)-> {
                    application.clrscr();
                    SearchHotel searchHotelPage = new SearchHotel((PagodaApp) application);
                    searchHotelPage.display();
                    searchHotelPage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                });
                application.clrscr();
                PagodaAppMenu roomMenu = new PagodaAppMenu((PagodaApp) application,"Room List",ops);
                roomMenu.display();
                roomMenu.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);

//                for(HashMap<String, String> item : viewRooms) {
//                    System.out.printf("%10s | %15s | %10s | %15s | %10s | %25s",item.get("roomID"),item.get("name"),item.get("roomSize"),
//                            item.get("pricePerNight"),item.get("capacity"),item.get("image"));

            } else {
                System.out.println("There is no room in this hotel!");
                application.pause();
                application.clrscr();
                SearchHotel searchHotelPage = new SearchHotel((PagodaApp) application);
                searchHotelPage.display();
                searchHotelPage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
            }

        } catch (SQLException e) {
            application.writeLog(e);
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
            application.pause();
        }
    }
}
